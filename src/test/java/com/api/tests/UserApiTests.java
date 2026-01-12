package com.api.tests;

import org.testng.annotations.Test;

import com.api.base.BaseTest;
import com.api.client.UserApiClient;

import io.restassured.response.Response;

import static org.testng.Assert.*;

import java.time.Instant;

public class UserApiTests extends BaseTest {

    // API client instance to call REST endpoints
    UserApiClient api = new UserApiClient();

    /**
     * Verify GET /users endpoint.
     * Confirms that the API returns a list of users.
     */
    @Test
    public void verifyGetUsers() {
    	
        Response response = api.getUsers();

        // Verify HTTP status code is 200 OK
        assertEquals(response.getStatusCode(), 200);

        // Ensure the response contains at least one user
        assertTrue(response.jsonPath().getList("$").size() > 0);
    }
    
    /**
     * Verify POST /users endpoint.
     * Creates a new user with dynamically generated unique values.
     */
    @Test
    public void verifyCreateUser() {
    	
        // Generate unique timestamp for name, email, and phone
        long timestamp = Instant.now().toEpochMilli();
        
        String name = "User" + timestamp;
        String email = "user" + timestamp + "@example.com";
        String phone = "9" + (100000000 + timestamp % 900000000); // Ensures unique 10-digit number

        // Create JSON payload dynamically
        String newUserJson = String.format("{\"name\":\"%s\",\"email\":\"%s\",\"phone\":\"%s\"}", 
                                            name, email, phone);

        // Call the createUser API
        Response response = api.createUser(newUserJson);

        // Validate response status code (200 or 201 depending on API)
        assertEquals(response.getStatusCode(), 200);

        // Validate the returned user ID is not null
        assertNotNull(response.jsonPath().getInt("id"));

        // Validate that the returned user name matches the input
        assertEquals(response.jsonPath().getString("name"), name);
    }
    
    /**
     * Verify DELETE /users/{id} endpoint.
     * Deletes the latest user entry dynamically.
     */
    @Test
    public void verifyDeleteLatestUser() {
    	
        // Get the ID of the latest user dynamically
        int latestId = api.getLatestUserId();

        // Call the deleteUser API
        Response deleteResponse = api.deleteUser(latestId);

        // Validate deletion status (200 OK or 204 No Content)
        assertEquals(deleteResponse.getStatusCode(), 200);

        // Optional verification: Ensure the deleted user no longer exists
        Response getResponse = api.getUsers();
        assertTrue(getResponse.jsonPath().getList("id").stream()
                      .noneMatch(id -> id.equals(latestId)));
    }
}
