package com.krafttech.apiTest.day07_POST_Request;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class PostRequestDemo {
    @BeforeClass
    public void beforeClass() {

        baseURI = "https://www.krafttechexlab.com/sw/api/v1";
    }

    @Test
    public void postNewUser() {
        String jsonBody = "{\n" +
                "  \"name\": \"aliveli\",\n" +
                "  \"email\": \"aliveli@krafttechexlab.com\",\n" +
                "  \"password\": \"1234678\",\n" +
                "  \"about\": \"Van\",\n" +
                "  \"terms\": \"2\"\n" +
                "}";

        Response response = given().accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .and()
                .body(jsonBody)
                .when()
                .post("/allusers/register");
        assertEquals(response.statusCode(), 200);
        response.prettyPrint();

        assertTrue(response.body().asString().contains("token"));
    }

    @Test
    public void postNewUser2() {
        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("name", "alivelim");
        requestMap.put("email", "alivelim@krafttechexlab.com");
        requestMap.put("password", "12345678");
        requestMap.put("about", "Van");
        requestMap.put("terms", "3");

        Response response = given().accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .and()
                .body(requestMap)
                .when()
                .post("/allusers/register");

        assertEquals(response.statusCode(), 200);
        response.prettyPrint();

        assertTrue(response.body().asString().contains("token"));
    }

    @Test
    public void postNewUser3() {
        NewUserInfo newUserInfo = new NewUserInfo();

        newUserInfo.setName("ali5");
        newUserInfo.setEmail("ali5@gmail.com");
        newUserInfo.setPassword("321654");
        newUserInfo.setAbout("Mus");
        newUserInfo.setTerms("3");

        Response response = given().accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .and()
                .body(newUserInfo)
                .when()
                .post("/allusers/register");

        assertEquals(response.statusCode(), 200);
        response.prettyPrint();

        assertTrue(response.body().asString().contains("token"));
    }

    @Test
    public void postNewUser4() {
        NewUserInfo newUserInfo = new NewUserInfo("ali6", "ali6@gmail.com", "523641", "Agrı", "2");
        Response response = given().accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .and()
                .body(newUserInfo)
                .when()
                .post("/allusers/register");

        assertEquals(response.statusCode(), 200);
        response.prettyPrint();

        assertTrue(response.body().asString().contains("token"));
    }

}
