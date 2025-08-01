package com.api.reqres.tests;

import org.testng.annotations.Test;

import com.api.reqres.base.BaseTest;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class GetUserTest extends BaseTest{

	@Test
	public void getUser_shouldReturnJanet() {
		given().
			when().get("/api/users/2").
				then().statusCode(200).body("data.first_name", equalTo("Janet"));
	}
	
	@Test
	public void getUser_shouldReturnUserDetail() {
		given().
			when().get("/api/users/2").
					then().statusCode(200).body("data.last_name",equalTo("Weaver"));
	}
	
	@Test
	public void getUser_allDetails() {
		given().
			contentType(ContentType.JSON).
				when().get("/api/users/2").
					then().statusCode(200)
							.body("data.id", equalTo(2))
							.body("data.first_name",equalTo("Janet"))
							.body("data.last_name", equalTo("Weaver"))
							.body("data.email", equalTo("janet.weaver@reqres.in"));
	}
	
	
	
	
	
	
	
}
