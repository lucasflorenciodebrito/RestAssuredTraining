package com.rest;

import io.restassured.config.LogConfig;

import static io.restassured.RestAssured.*;
import static io.restassured.config.LogConfig.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import  static org.hamcrest.Matchers.*;

public class Logging {

    @org.testng.annotations.Test
    public void requestResponseLogging(){
        given()
                .baseUri("https://api.getpostman.com")
                .header("X-Api-Key", "PMAK-620acd408d8f425877707f21-946f9db38d7188fed5d7a86520ed2add77")
                .config(config.logConfig(LogConfig.logConfig().enableLoggingOfRequestAndResponseIfValidationFails()))
        .when()
                .get("/workspaces")
        .then()
                //.log().ifValidationFails()
                .assertThat()
                .statusCode(200);
    }
}
