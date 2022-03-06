package com.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rest.pojo.SimplePojo;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class SimplePojoExample {
    private static RequestSpecification requestSpecification;

    @BeforeClass
    public void beforeClass(){

        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.setBaseUri("https://postman-echo.com");
        requestSpecBuilder.setContentType(ContentType.JSON);

        requestSpecification = requestSpecBuilder.build();

    }

    @Test
    public void simplePojoExample() throws JsonProcessingException {
        //serialization
        SimplePojo simplePojo = new SimplePojo();
        simplePojo.setKey1("value1");
        simplePojo.setKey2("value2");

        SimplePojo deserializaedPojo = new SimplePojo();

        deserializaedPojo = given().spec(requestSpecification)
                .baseUri("https://postman-echo.com")
                .body(simplePojo)
                .log().all()
        .when()
                .post("/post")
        .then()
                .log().all()
                .extract()
                .response()
                .as(SimplePojo.class);

        //desserialization
        ObjectMapper objectMapper = new ObjectMapper();
        String deserializedPojoStr = objectMapper.writeValueAsString(deserializaedPojo);
        String simplePojoStr = objectMapper.writeValueAsString(simplePojo);
        assertThat(objectMapper.readTree(deserializedPojoStr),equalTo(objectMapper.readTree(simplePojoStr)));

    }
}
