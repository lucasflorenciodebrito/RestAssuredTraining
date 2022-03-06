package com.rest;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.QueryableRequestSpecification;
import io.restassured.specification.SpecificationQuerier;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.config.LogConfig.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import  static org.hamcrest.Matchers.*;
import io.restassured.specification.RequestSpecification;

public class RequestEspecificationExample {
    RequestSpecification requestSpecification;

    @BeforeClass
    public void beforeClass(){
//        requestSpecification = with()
//                .baseUri("https://api.getpostman.com")
//                .header("X-Api-Key", "PMAK-620acd408d8f425877707f21-946f9db38d7188fed5d7a86520ed2add77");

        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.setBaseUri("https://api.getpostman.com");
        requestSpecBuilder.addHeader("X-Api-Key", "PMAK-620acd408d8f425877707f21-946f9db38d7188fed5d7a86520ed2add77");

        requestSpecification = requestSpecBuilder.build();

    }

    @Test
    public  void validateStatusCode(){
        given().spec(requestSpecification)
        .when()
                .get("/workspaces")
        .then()
                .assertThat()
                .statusCode(200);

    }

    @Test
    public  void validateResponseBody(){
        given().spec(requestSpecification)
        .when()
                .get("/workspaces")
        .then()
                .assertThat()
                .statusCode(200)
                .body("workspaces.name", hasItems("Team Workspace", "myworkspace1", "My Workspace", "Bootcamp"))
                .body("workspaces[0].name", equalTo("Team Workspace"));

    }

    @Test
    public void queryTest(){
        QueryableRequestSpecification queryableRequestSpecification = SpecificationQuerier
                .query(requestSpecification);
    }

}
