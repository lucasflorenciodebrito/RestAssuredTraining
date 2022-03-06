package com.rest;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.config.LogConfig.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import  static org.hamcrest.Matchers.*;

public class ResponseHeader {

    @Test
    public void assertResponseHeaders(){
        Header header1 = new Header("header","value2");
        Header header2 = new Header("x-mock-match-request-headers","header");
        Headers headers = new Headers(header1,header2);
        given()
                .baseUri("https://8f6d7436-aba9-4c1f-bc81-fdc881a11fb1.mock.pstmn.io")
                .headers(headers)
        .when()
                .get("/get")
        .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .headers("responseHeader", "resValue2", "X-RateLimit-Limit", "120");
    }

    @Test
    public void extractHeaders(){
        Header header1 = new Header("header","value2");
        Header header2 = new Header("x-mock-match-request-headers","header");
        Headers headers = new Headers(header1,header2);

        Headers extractedHeaders = given()
                .baseUri("https://8f6d7436-aba9-4c1f-bc81-fdc881a11fb1.mock.pstmn.io")
                .headers(headers)
        .when()
                .get("/get")
        .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .extract().headers();

            for (Header header: extractedHeaders){
                System.out.println("header name: "+ header.getName()+ ", ");
                System.out.println("header value: "+ header.getValue());
            }

        System.out.println("header name: "+ extractedHeaders.get("responseHeader").getName());
        System.out.println("header value: "+ extractedHeaders.get("responseHeader").getValue());




    }

}
