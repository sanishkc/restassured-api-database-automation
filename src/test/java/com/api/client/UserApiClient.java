package com.api.client;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class UserApiClient {

    public Response getUsers() {
        return given()
                .contentType(ContentType.JSON)
                .when()
                .get("/users");
    }

    // 🔜 Future extensions
    public Response createUser(String body) {
        return given()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post("/users");
    }

    public Response deleteUser(int id) {
        return when()
                .delete("/users/" + id);
    }
}
