package com.rest;

import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class PathParameter {

    @Test
    public void path_parameter(){
        HashMap<String, String> pathParameters = new HashMap<String, String>();
        pathParameters.put("userId", "2");
        pathParameters.put("bookId", "1");

        given()
                .baseUri("https://resqres.in")
                .pathParams(pathParameters)
//                .pathParam("userId", "2")
//                .pathParam("bookId", "1")
                .log().all()
        .when()
                .get("/api/users/{userId}/{bookId}")
        .then()
                .log().all()
                .assertThat()
                .statusCode(200);
    }
}
