package com.rest;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.equalTo;

public class PostExample {
    RequestSpecification requestSpecification;

    @BeforeClass
    public void beforeClass(){
//        requestSpecification = with()
//                .baseUri("https://api.getpostman.com")
//                .header("X-Api-Key", "PMAK-620acd408d8f425877707f21-946f9db38d7188fed5d7a86520ed2add77");

        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder()
                .setBaseUri("https://api.getpostman.com")
                .addHeader("X-Api-Key", "PMAK-620acd408d8f425877707f21-946f9db38d7188fed5d7a86520ed2add77")
                .setContentType(ContentType.JSON);


        requestSpecification = requestSpecBuilder.build();

    }

    @Test
    public  void PostExample(){
        String payload = "{\n" +
                "    \"workspace\":{\n" +
                "        \"name\": \"myFirstWorkspace\",\n" +
                "        \"type\": \"personal\",\n" +
                "        \"description\": \"Rest Assured created this\"\n" +
                "    }\n" +
                "    \n" +
                "}";
        given().spec(requestSpecification)
                .body(payload)
        .when()
                .post("/workspaces")
        .then()
                .assertThat()
                .body("workspace.name", equalTo("myFirstWorkspace"));

    }

    @Test
    public void validatePostRequestPayloadAsMap(){
        HashMap<String, Object> mainObject = new HashMap<String, Object>();

        HashMap<String, String> nestedObject = new HashMap<String, String>();
        nestedObject.put("name", "myWorkspaceAgain");
        nestedObject.put("type", "personal");
        nestedObject.put("description", "Rest Assured created this");

        mainObject.put("workspace", nestedObject);

        given().spec(requestSpecification)
                .body(mainObject)
        .when()
                .log().all()
                .post("/workspaces")
        .then()
                .log().all()
                .assertThat()
                .body("workspace.name", equalTo("myFirstWorkspaceAgain"));

    }


}
