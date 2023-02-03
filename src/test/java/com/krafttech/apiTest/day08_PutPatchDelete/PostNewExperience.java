package com.krafttech.apiTest.day08_PutPatchDelete;

import org.testng.annotations.BeforeClass;

import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;


public class PostNewExperience {
    @BeforeClass
    public void beforeClass() {

        baseURI = "https://www.krafttechexlab.com/sw/api/v1";
    }

    @Test
    public void newExperience() {

        String body = "{\n" +
                "  \"job\": \"QA \",\n" +
                "  \"company\": \"IT Techex\",\n" +
                "  \"location\": \"TR\",\n" +
                "  \"fromdate\": \"2011-12-01\",\n" +
                "  \"todate\": \"2012-12-01\",\n" +
                "  \"current\": \"false\",\n" +
                "  \"description\": \"Great Job\"\n" +
                "}";

        Response response = given().accept(ContentType.JSON)
                .queryParam("token", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJhdXQiOiJhRm0iLCJsaW5rIjoia3JhZnR0ZWNoZXhsYWIuY29tIiwidXNlcmlkIjoiMjk1Iiwic3RhcnQiOjE2NzQ4Mzg4MTIsImVuZHMiOjE2NzU0NDM2MTJ9.DmnWJsq9zOgbQCP166xi0TFvlwr-9MFWQVymaFJa43eVOkILgXuiXX4P0X7DACct252NMcKOWs6vaR8Wd2FAyQ")
                .body(body)
                .when().log().all()
                .post("/experience/add").prettyPeek();

        //"id1: 230
        //"id2: 243

    }

}
