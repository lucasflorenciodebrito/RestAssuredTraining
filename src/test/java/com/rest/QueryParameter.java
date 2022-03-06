package com.rest;

import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class QueryParameter {
    @Test
    public void singleQueryParameter(){

        given()
                .baseUri("https://postman-echo.com")
                .param("foo1","bar1")
                .log().all()
        .when()
                .get("/get")
        .then()
                .log().all()
                .assertThat()
                .statusCode(200);
    }



    @Test
    public void multipleQueryParameter(){

        HashMap<String,String> queryParameter = new HashMap<String, String>();
        queryParameter.put("foo1", "bar1");
        queryParameter.put("foo2", "bar2");

        given()
                .baseUri("https://postman-echo.com")
//                .queryParam("foo1", "bar1")
//                .queryParam("foo2", "bar2")
                .queryParams(queryParameter)
                .log().all()
        .when()
                .get("/get")
        .then()
                .log().all()
                .assertThat()
                .statusCode(200);
    }

    @Test
    public void multipleValueQueryParameter(){
//      https://postman-echo.com/get?foo1=bar1,bar2,bar3


        given()
                .baseUri("https://postman-echo.com")
//                .queryParam("foo1", "bar1")
//                .queryParam("foo2", "bar2")
                .queryParam("foo1", "bar1", "bar2", "bar3")
                .log().all()
         .when()
                .get("/get")
         .then()
                .log().all()
                .assertThat()
                .statusCode(200);
    }



}
