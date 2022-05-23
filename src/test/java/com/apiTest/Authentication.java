package com.apiTest;

import org.testng.annotations.Test;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class Authentication {
	
	static String refreshtokenvalue ;
	
	@Test
	public void TokenCreation()
	{
		String b = "{\n"
				+ "	\"email\": \"sharath.sep05@gmail.com\",\n"
				+ "    \"password\": \"123456\"\n"
				+ "}";
		
		RestAssured.baseURI="https://api.baze.com";
		
		Response res = given().contentType(ContentType.JSON).body(b).when()
				.post("/auth/token").then().assertThat().statusCode(200).extract().response();
	
	System.out.println(res.asString());
	
	String responsejson = res.asString();
	
	JsonPath js = new JsonPath(responsejson);
	 refreshtokenvalue = js.get("refresh");
	System.out.println(refreshtokenvalue);
	
	}
	
	@Test(dependsOnMethods = "TokenCreation")
	public void PasswordRest()
	{
		
		String b = "{\n"
				+ "	\"email\": \"sharath.sep05@gmail.com\"\n"
				+ "	\n"
				+ "}";
		
		Response res = given().contentType(ContentType.JSON).
				header("Authorization",Authentication.refreshtokenvalue).body(b)
				.when().post("/user/password/reset").then().assertThat().statusCode(201)
				.extract().response();
		
		
		System.out.println(res.asString());
		
	}

}
