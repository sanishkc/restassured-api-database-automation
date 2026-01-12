package com.api.client;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.util.List;
import java.util.Map;

public class UserApiClient {

    /**
     * Sends a GET request to /users endpoint.
     * 
     * @return Response object containing the list of all users.
     */
    public Response getUsers() {
        return given()
                .contentType(ContentType.JSON) // Specify that the request expects JSON
                .when()
                .get("/users");               // Send GET request to /users
    }

    /**
     * Sends a POST request to /users endpoint to create a new user.
     * 
     * @param body JSON string representing the new user data
     * @return Response object containing the created user info
     */
    public Response createUser(String body) {
        return given()
                .contentType(ContentType.JSON) // Send request with JSON body
                .body(body)                     // Attach user payload
                .when()
                .post("/users");               // Send POST request to /users
    }
    
    /**
     * Sends a DELETE request to /users/{id} endpoint to remove a specific user.
     * 
     * @param id ID of the user to delete
     * @return Response object containing status of deletion
     */
    public Response deleteUser(int id) {
        return when()
                .delete("/users/" + id);       // Send DELETE request for user with given ID
    }

    /**
     * Retrieves the ID of the latest user based on the highest ID in the list.
     * Useful for deleting the most recently added user dynamically.
     * 
     * @return ID of the latest user
     * @throws RuntimeException if no users are found
     */
    public int getLatestUserId() {
        Response response = getUsers();                         // Get all users
        List<Map<String, Object>> users = response.jsonPath().getList("$"); // Parse JSON as List<Map>

        if (users.isEmpty()) {
            throw new RuntimeException("No users found to delete");
        }

        // Find the user with the maximum ID
        return users.stream()
                    .mapToInt(user -> (Integer) user.get("id"))
                    .max()
                    .getAsInt();
    }
}