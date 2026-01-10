package com.api.tests;

import org.testng.annotations.Test;

import com.api.base.BaseTest;
import com.api.client.UserApiClient;

import io.restassured.response.Response;

import static org.testng.Assert.*;

public class UserApiTests extends BaseTest {

    UserApiClient api = new UserApiClient();

    @Test
    public void verifyGetUsers() {

        Response response = api.getUsers();

        assertEquals(response.getStatusCode(), 200);
        assertTrue(response.jsonPath().getList("$").size() > 0);

        System.out.println(response.asPrettyString());
    }
}
