package com.api.base;

import org.testng.annotations.BeforeClass;
import com.api.utils.ConfigManager;
import io.restassured.RestAssured;

public class BaseTest {

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = ConfigManager.get("base.url");
    }
}
