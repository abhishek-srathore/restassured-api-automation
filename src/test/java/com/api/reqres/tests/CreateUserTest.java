package com.api.reqres.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.api.reqres.base.BaseTest;
import com.api.reqres.payloads.PayLoadBuilder;
import com.api.reqres.utilty.ConfigReader;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.restassured.http.ContentType;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import io.qameta.allure.testng.AllureTestNg;
import static io.restassured.module.jsv.JsonSchemaValidator.*;

@Listeners({AllureTestNg.class})
@Epic("User Management")
@Feature("Create User")
public class CreateUserTest extends BaseTest {


	@Story("POST Create a new user")
	@Severity(SeverityLevel.CRITICAL)
	@Test(description = "Verify user creation with valid data")
	public void creatUser_shouldReturn201andValidFiels() {
		String body = PayLoadBuilder.createUserPayload("Abhishek", "Tester");

		given().contentType(ContentType.JSON).header("x-api-key", ConfigReader.getProperty("key")).body(body).when()
				.post("/api/users").then().statusCode(201).body("name", equalTo("Abhishek"))
				.body("job", equalTo("Tester")).body("id", notNullValue()).body("createdAt", notNullValue());
	}
	

	
	@DataProvider(name = "negativeData")
	public Object[][] negativeTestData() {
	    return new Object[][]{
	    	{""								,	"No Token",					"{\"name\":\"John\"}", 							401},
	    	{"Invalid Token"				,	"InValid Token",			"{\"name\":\"John\"}", 							403},
	        {ConfigReader.getProperty("key"),	"Missing required Field",	"{\"name\":\"John\"}", 							400},
	        {ConfigReader.getProperty("key"),	"Empty body",				"",												400},
	        {ConfigReader.getProperty("key"),	"Invalid type", 			"{\"name\":123,\"job\":true}",					400},
	        {ConfigReader.getProperty("key"),	"Invalid JSON body", 		"{ name: \"Abhishek\", job: \"Tester\" }",		400},
	        
	    };
	}

	@Test(dataProvider = "negativeData")
	public void testCreateUserNegative(String authKey, String scenerio, String body, int expectedStatus) {
		given()
			.contentType(ContentType.JSON)
			.header("x-api-key", authKey)
			.body(body)
		.when()
			.post("/api/users")
		.then()
			.statusCode(expectedStatus);
	};
	
	@Test
	public void createUser_shouldMatchJsonSchema() {
	    String body = PayLoadBuilder.createUserPayload("Abhishek", "Tester");

	    given()
	        .contentType(ContentType.JSON)
	        .body(body)
	        .header("x-api-key", ConfigReader.getProperty("key"))
	    .when()
	        .post("/api/users")
	    .then()
	        .statusCode(201)
	        .assertThat()
	        .body(matchesJsonSchemaInClasspath("schema/create_user_schema.json"));
	}
	
}
