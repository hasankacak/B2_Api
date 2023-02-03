package com.krafttech.apiTest.day08_PutPatchDelete;

import org.testng.annotations.BeforeClass;

import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class PutExperience {
    @BeforeClass
    public void beforeClass() {

        baseURI = "https://www.krafttechexlab.com/sw/api/v1";
    }

    @Test
    public void updateExperience() {


        String body = "{\n" +
                "  \"job\": \"Developer\",\n" +
                "  \"company\": \"Kraft Techex\",\n" +
                "  \"location\": \"England\",\n" +
                "  \"fromdate\": \"2012-05-01\",\n" +
                "  \"todate\": \"2018-03-01\",\n" +
                "  \"current\": \"true\",\n" +
                "  \"description\": \"experience update\"\n" +
                "}";

        Response response = given().accept(ContentType.JSON)
                .queryParam("token", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJhdXQiOiJhRm0iLCJsaW5rIjoia3JhZnR0ZWNoZXhsYWIuY29tIiwidXNlcmlkIjoiMjk1Iiwic3RhcnQiOjE2NzQ4Mzg4MTIsImVuZHMiOjE2NzU0NDM2MTJ9.DmnWJsq9zOgbQCP166xi0TFvlwr-9MFWQVymaFJa43eVOkILgXuiXX4P0X7DACct252NMcKOWs6vaR8Wd2FAyQ")
                .queryParam("id", 230)
                .body(body)
                .when().log().all()
                .put("/experience/updateput").prettyPeek();

    }

}
