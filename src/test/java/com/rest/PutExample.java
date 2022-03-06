package com.rest;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PutExample {

    RequestSpecification requestSpecification;

    @BeforeClass
    public void beforeClass(){
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder()
                .setBaseUri("https://api.getpostman.com")
                .addHeader("X-Api-Key", "PMAK-620acd408d8f425877707f21-946f9db38d7188fed5d7a86520ed2add77")
                .setContentType(ContentType.JSON);


        requestSpecification = requestSpecBuilder.build();

    }

    @Test
    public  void PutExample(){
        String workspaceId = "845b7bf8-3544-40d9-8d13-f06014931816";
        String payload = "{\n" +
                "    \"workspace\":{\n" +
                "        \"name\": \"myFirstWorkspacePut\",\n" +
                "        \"type\": \"personal\",\n" +
                "        \"description\": \"Rest Assured created this\"\n" +
                "    }\n" +
                "    \n" +
                "}";
        given().spec(requestSpecification)
                .body(payload)
                .pathParam("workspaceId", workspaceId )
                .log().all()
        .when()
                .put("/workspaces/{workspaceId}")
        .then()
                .assertThat()
                .body("workspace.name", equalTo("myFirstWorkspacePut"));

    }

}
