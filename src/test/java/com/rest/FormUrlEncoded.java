package com.rest;

import io.restassured.config.EncoderConfig;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class FormUrlEncoded {

    @Test
    public void formUrlEncoded(){
        given()
                .baseUri("https://postman-echo.com")
                .config(config().encoderConfig(EncoderConfig.encoderConfig()
                        .appendDefaultContentCharsetToContentTypeIfUndefined(false)))
                .formParam("key1", "value1")
                .formParam("key 2", "value 2")
                .log().all()
        .when()
                .post("/post")
        .then()
                .log().all()
                .assertThat()
                .statusCode(200);

    }

}
