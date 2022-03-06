package com.rest;
import static io.restassured.RestAssured.*;
import static io.restassured.config.LogConfig.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import  static org.hamcrest.Matchers.*;

public class ValidateResponseBodyHamcrest {
    @org.testng.annotations.Test
    public void validateResponseBodyLearningHamcrest(){
        given()
                .baseUri("https://api.getpostman.com")
                .header("X-Api-Key", "PMAK-620acd408d8f425877707f21-946f9db38d7188fed5d7a86520ed2add77")
        .when()
                .get("/workspaces")
        .then()
                .assertThat()
                .statusCode(200)
                .body("workspaces.name", contains("Team Workspace", "myworkspace1", "My Workspace", "Bootcamp", "Study SDET-QA", "Projeto Final - Udemy"),
                        "workspaces.name", is(not(empty())),
                        "workspaces.name", hasSize(6),
                        "workspaces[0]", hasKey("id"),
                        "workspaces[0]", hasValue("Team Workspace"),
                        "workspaces[0]", hasEntry("id", "c429457e-5f96-4f34-ab92-eaeef394cc6e")
                );

    }
}
