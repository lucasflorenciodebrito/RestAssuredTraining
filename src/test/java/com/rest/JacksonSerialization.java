package com.rest;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class JacksonSerialization {
    private static RequestSpecification requestSpecification;

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
    public void validatePostRequestPayloadAsMap() throws JsonProcessingException {
        HashMap<String, Object> mainObject = new HashMap<String, Object>();
        HashMap<String, String> nestedObject = new HashMap<String, String>();
        nestedObject.put("name", "myThirdWorkspace");
        nestedObject.put("type", "personal");
        nestedObject.put("description", "Rest Assured created this");

        mainObject.put("workspace", nestedObject);

        ObjectMapper objectMapper = new ObjectMapper();
        String mainObjectStr = objectMapper.writeValueAsString(mainObject);

        given().spec(requestSpecification)
                .baseUri("https://api.getpostman.com")
                .body(mainObjectStr)
                .log().all()
        .when()
                .post("/workspaces")
        .then()
                .assertThat()
                .body("workspace.name", equalTo("myThirdWorkspace"));
    }
}
