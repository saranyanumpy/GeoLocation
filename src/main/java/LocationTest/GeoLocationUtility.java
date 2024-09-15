package LocationTest;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class GeoLocationUtility {

    private static final String API_KEY = "f897a99d971b5eef57be6fafa0d83239";
    private static final String BASE_URL = "http://api.openweathermap.org/geo/1.0/";

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Please provide a location (e.g., \"San Francisco, CA\" or ZIP code).");
            return;
        }

        for (String location : args) {
            if (location.matches("\\d{5}")) {
                // It's a ZIP code
                getGeoDataByZip(location);
            } else {
                // It's a city 
                getGeoDataByCity(location);
            }
        }
    }

    public static void getGeoDataByCity(String city) {
    	try {
    	    //  city name
    	    String encodedCity = URLEncoder.encode(city, StandardCharsets.UTF_8.toString());
    	    String apiUrl = BASE_URL + "direct?q=" + encodedCity + "&limit=1&appid=" + API_KEY;

    	    // Debugging 
    	    System.out.println("Encoded City: " + encodedCity);
    	    System.out.println("API URL: " + apiUrl);

    	    // API call
    	    Response response = RestAssured.get(apiUrl);

    	    // Response status is OK
    	    if (response.getStatusCode() == 200) {
    	        String responseBody = response.getBody().asString();
    	        JSONArray jsonArray = new JSONArray(responseBody);

    	        if (jsonArray.length() > 0) {
    	            JSONObject locationData = jsonArray.getJSONObject(0);
    	            System.out.println("City: " + city);
    	            System.out.println("Latitude: " + locationData.getDouble("lat"));
    	            System.out.println("Longitude: " + locationData.getDouble("lon"));
    	            System.out.println("Place Name: " + locationData.getString("name"));
    	        } else {
    	            System.out.println("No results found for " + city);
    	        }
    	    } else {
    	        System.out.println("Error fetching data for " + city + ". Response Code: " + response.getStatusCode());
    	    }
    	} catch (UnsupportedEncodingException e) {
            System.out.println("Error encoding city name: " + city);
            e.printStackTrace();
        } catch (JSONException e) {
            System.out.println("Error parsing response for " + city);
            e.printStackTrace();
        }
    }
   
    public static void getGeoDataByZip(String zipCode) {
        // API call for ZIP code
        String apiUrl = BASE_URL + "zip?zip=" + zipCode + ",US&appid=" + API_KEY;

        Response response = RestAssured.get(apiUrl);

        if (response.getStatusCode() == 200) {
            JSONObject locationData = new JSONObject(response.getBody().asString());
            System.out.println("ZIP Code: " + zipCode);
            System.out.println("Latitude: " + locationData.getDouble("lat"));
            System.out.println("Longitude: " + locationData.getDouble("lon"));
            System.out.println("Place Name: " + locationData.getString("name"));
            System.out.println("----------------------");
        } else {
            System.out.println("Error fetching data for ZIP code: " + zipCode);
        }
    }
}

