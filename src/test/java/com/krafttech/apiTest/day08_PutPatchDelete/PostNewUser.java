package com.krafttech.apiTest.day08_PutPatchDelete;

import org.testng.annotations.BeforeClass;

import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import org.testng.annotations.Test;


import static io.restassured.RestAssured.given;

public class PostNewUser {
    @BeforeClass
    public void beforeClass() {

        baseURI = "https://www.krafttechexlab.com/sw/api/v1";
    }

    @Test
    public void NewUser() {

        String body = "{\n" +
                "  \"name\": \"aFmhsn\",\n" +
                "  \"email\": \"devhsn@krafttechexlab.com\",\n" +
                "  \"password\": \"1234678\",\n" +
                "  \"about\": \"About Me\",\n" +
                "  \"terms\": \"10\"\n" +
                "}";

        Response response = given().accept(ContentType.JSON)
                .body(body)
                .when().log().all()
                .post("/allusers/register").prettyPeek();

        String token = response.path("token");
        System.out.println("token = " + token);
    }
//eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJhdXQiOiJhRm0iLCJsaW5rIjoia3JhZnR0ZWNoZXhsYWIuY29tIiwidXNlcmlkIjoiMjk1Iiwic3RhcnQiOjE2NzQ4Mzg4MTIsImVuZHMiOjE2NzU0NDM2MTJ9.DmnWJsq9zOgbQCP166xi0TFvlwr-9MFWQVymaFJa43eVOkILgXuiXX4P0X7DACct252NMcKOWs6vaR8Wd2FAyQ
}
