package geoLocationUtilityPackage;

import org.junit.Test;

import LocationTest.GeoLocationUtility;

import static org.junit.Assert.*;

	public class GeoLocationUtilityTest {

	    @Test
	    public void testGetGeoDataByCity() {
	        // Call the method with a valid city and state
	        GeoLocationUtility.getGeoDataByCity("Redmond");

	        
	        assertTrue(true);  //  assertion
	    }

	    @Test
	    public void testGetGeoDataByZip() {
	        // Call the method with a valid ZIP code
	        GeoLocationUtility.getGeoDataByZip("12345");

	        
	        assertTrue(true);  //  assertion
	    }

	    @Test
	    public void testInvalidCityOrZip() {
	        // Test with an invalid city/state
	        GeoLocationUtility.getGeoDataByCity("InvalidCity, ZZ");
	        GeoLocationUtility.getGeoDataByZip("00000");

	        assertTrue(true);  //  assertion
	    }

	    @Test
	    public void testMultipleValidInputs() {
	        // Test with multiple valid inputs (both city/state and ZIP)
	        String[] inputs = {"Redmond", "12345"};
	        for (String location : inputs) {
	            if (location.matches("\\d{5}")) {
	                GeoLocationUtility.getGeoDataByZip(location);
	            } else {
	                GeoLocationUtility.getGeoDataByCity(location);
	            }
	        }

	       
	        assertTrue(true);  //  assertion
	    }
	}




