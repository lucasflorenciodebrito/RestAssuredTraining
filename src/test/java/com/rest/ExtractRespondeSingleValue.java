package com.rest;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;

import static io.restassured.RestAssured.*;
import static io.restassured.config.LogConfig.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import  static org.hamcrest.Matchers.*;

public class ExtractRespondeSingleValue {

    @org.testng.annotations.Test
    public void extractSingleValue(){
        String res =
                given()
                        .baseUri("https://api.getpostman.com")
                        .header("X-Api-Key", "PMAK-620acd408d8f425877707f21-946f9db38d7188fed5d7a86520ed2add77")
                .when()
                        .get("/workspaces")
                .then()
                        .assertThat()
                        .statusCode(200)
                        .extract().response().asString();
        //assertThat(JsonPath.from(res).getString("workspaces[0].name"), equalTo("Team Workspace"));
        Assert.assertEquals(JsonPath.from(res).getString("workspaces[0].name"), "Team Workspace");
        //System.out.println("workspaces name: " + JsonPath.from(res).getString("workspaces[0].name"));


    }
}
