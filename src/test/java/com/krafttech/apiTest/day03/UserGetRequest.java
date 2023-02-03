package com.krafttech.apiTest.day03;

import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UserGetRequest {

    String exLapURL = "https://www.krafttechexlab.com/sw/api/v1";

    /**
     * get all users
     */

    @Test
    public void test1() {
        Response response = given().accept(ContentType.JSON)
                .queryParam("pagesize", 50)
                .queryParam("page", 1)
                .and().when()
                .get(exLapURL + "/allusers/alluser");

        System.out.println("response.statusCode() = " + response.statusCode());
        response.prettyPrint();
    }

    /*
   TASK
        When user sends a GET request to /allusers/getbyid/111
        Then Status code should be 200
        And content type should be application/json; charset=UTF-8
        And json body should contain Thomas Eduson
    */
    @Test
    public void test2() {
        Response response = given().accept(ContentType.JSON)
                .pathParam("id", "111")
                .when()
                .get(exLapURL + "/allusers/getbyid/{id}");

        Assert.assertEquals(response.statusCode(), 200);
        //Assert.assertEquals(response.contentType(),"application/json; charset=UTF-8");

        Assert.assertEquals(response.header("Content-Type"), "application/json; charset=UTF-8");
        Assert.assertEquals(response.header("Content-Length"), "636");
        Assert.assertTrue(response.body().asString().contains("Thomas Eduson"));
        System.out.println("response.statusCode() = " + response.statusCode());
        //response.prettyPrint();
    }

}
