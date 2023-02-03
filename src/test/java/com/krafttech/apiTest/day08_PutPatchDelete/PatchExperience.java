package com.krafttech.apiTest.day08_PutPatchDelete;

import org.testng.annotations.BeforeClass;

import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class PatchExperience {
    @BeforeClass
    public void beforeClass() {

        baseURI = "https://www.krafttechexlab.com/sw/api/v1";
    }

    @Test
    public void patchExperience() {

        String body = "{\n" +
                "  \"job\": \"SDET\",\n" +
                "  \"company\": \"Kraft Techex\",\n" +
                "  \"location\": \"TR\",\n" +
                "  \"fromdate\": \"2010-05-01\",\n" +
                "  \"todate\": \"2015-03-01\",\n" +
                "  \"current\": \"true\",\n" +
                "  \"description\": \"experience update\"\n" +
                "}";

        Response response = given().accept(ContentType.JSON)
                .pathParam("id", 230)
                .queryParam("token", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJhdXQiOiJhRm0iLCJsaW5rIjoia3JhZnR0ZWNoZXhsYWIuY29tIiwidXNlcmlkIjoiMjk1Iiwic3RhcnQiOjE2NzQ4Mzg4MTIsImVuZHMiOjE2NzU0NDM2MTJ9.DmnWJsq9zOgbQCP166xi0TFvlwr-9MFWQVymaFJa43eVOkILgXuiXX4P0X7DACct252NMcKOWs6vaR8Wd2FAyQ")
                .body(body)
                .when().log().all()
                .patch("/experience/updatepatch/{id}").prettyPeek();

        //assertEquals(response.statusCode(),200);

    }

}
