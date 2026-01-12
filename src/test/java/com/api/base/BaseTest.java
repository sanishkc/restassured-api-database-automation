package com.api.base;

import org.testng.annotations.BeforeClass;
import com.api.utils.ConfigManager;
import io.restassured.RestAssured;

public class BaseTest {

    /**
     * This method runs before any test class execution (TestNG @BeforeClass).
     * It sets the base URL for all RestAssured API calls.
     * 
     * Steps:
     * 1. Reads the 'base.url' property from config.properties using ConfigManager.
     * 2. Ensures there is no trailing slash at the end of the URL (RestAssured best practice).
     */
    @BeforeClass
    public void setup() {
        // Fetch base URL from config.properties (can come from environment variable if set)
        RestAssured.baseURI = ConfigManager.get("base.url").replaceAll("/$", "");
    }
}
