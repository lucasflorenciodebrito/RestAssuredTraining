package com.rest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import static io.restassured.RestAssured.*;
import static io.restassured.config.LogConfig.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import  static org.hamcrest.Matchers.*;

public class StatusCode {

    @org.testng.annotations.Test
    public void getStatusCode(){
        given()
                .baseUri("https://api.getpostman.com")
                .header("X-Api-Key", "PMAK-620acd408d8f425877707f21-946f9db38d7188fed5d7a86520ed2add77")
        .when()
                .get("/workspaces")
        .then()
                .assertThat()
                .statusCode(200)
                .body("workspaces.name", hasItems("Team Workspace", "myworkspace1", "My Workspace", "Bootcamp"))
                .body("workspaces[0].name", equalTo("Team Workspace"));


    }
}
