package com.krafttech.apiTest.day07_POST_Request;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class PostEducation {

    @BeforeClass
    public void beforeClass() {

        baseURI = "https://www.krafttechexlab.com/sw/api/v1";
    }

    @Test
    public void postNewUser() {
        NewUserInfo newUserInfo = new NewUserInfo("ali7", "ali7@gmail.com", "523631", "Agrı", "2");
        Response response = given().accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .and()
                .body(newUserInfo)
                .when()
                .post("/allusers/register");

        assertEquals(response.statusCode(), 200);
        response.prettyPrint();

        String token = response.path("token");

        String educationBody = "{\n" +
                "  \"school\": \"Kraft School\",\n" +
                "  \"degree\": \"B2\",\n" +
                "  \"study\": \"Test\",\n" +
                "  \"fromdate\": \"2020-01-01\",\n" +
                "  \"todate\": \"2020-08-01\",\n" +
                "  \"current\": \"false\",\n" +
                "  \"description\": \"Sdet Camp\"\n" +
                "}";
        response = given().accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .and()
                .body(educationBody)
                .and()
                .queryParam("token", token)
                .when()
                .post("/education/add");

        assertEquals(response.statusCode(), 200);
        response.prettyPrint();
    }

    @Test
    public void postNewUserAndVerify() {

        String name = "ali10";
        String email = "ali10@gmail.com";
        String password = "ali10";
        String about = "afm";
        String terms = "7";

        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("name", name);
        requestMap.put("email", email);
        requestMap.put("password", password);
        requestMap.put("about", about);
        requestMap.put("terms", terms);

        Response response = given().accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .and()
                .body(requestMap)
                .and()
                .when()
                .post("/allusers/register");

        assertEquals(response.statusCode(), 200);
        response.prettyPrint();

        String token = response.path("token");
        //String userID= response.path("id");

        Map<String, Object> educationBody = new HashMap<>();
        educationBody.put("school", "Kraft School");
        educationBody.put("degree", "B2");
        educationBody.put("study", "Test");
        educationBody.put("fromdate", "2020-01-01");
        educationBody.put("todate", "2020-08-01");
        educationBody.put("current", "false");
        educationBody.put("description", "Sdet Camp");

        response = given().accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .and()
                .body(educationBody)
                .and()
                .queryParam("token", token)
                .when()
                .post("/education/add");
        assertEquals(response.statusCode(), 200);
        response.prettyPrint();

        //verify body
        int id = response.path("id");
        response = given().accept(ContentType.JSON)
                .and()
                .queryParam("token", token)
                .pathParam("id", id)
                .when()
                .get("/education/getbyid/{id}");

        assertEquals(response.statusCode(), 200);
        response.prettyPrint();

        //verify with path
        //System.out.println("userID = " + userID);
        System.out.println("education id = " + id);
        //assertEquals(response.path("userid"),userID);
        assertEquals(response.path("school"), "Kraft School");

        //verify using hamcrest matcher

        given().accept(ContentType.JSON)
                .and()
                .queryParam("token", token)
                .pathParam("id", id)
                .when()
                .get("/education/getbyid/{id}")
                .then()
                .assertThat()
                .body("school", equalTo("Kraft School"), "study"
                        , equalTo("Test")).log().all();

    }

}

