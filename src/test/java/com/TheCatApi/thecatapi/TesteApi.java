package com.TheCatApi.thecatapi;

import org.junit.Test;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class TesteApi {
	
	String votoId;
	
	@Test
	public void cadastro() {

		// endPoint     
		String url = "https://api.thecatapi.com/v1/user/passwordlesssignup";
		
		// body com dados do endpoint
		String body = "{\r\n" + "    \"email\": \"randellr7@gmail.com\", \r\n"
				+ "    \"appDescription\": \"testeapiTheCat\"\r\n" + "}";

		// given
		Response result = given().contentType("application/json").body(body).
		
		// when
		when().post(url);
		
		// then
		result.then().statusCode(400);
		
		System.out.println("resultado: " + result.body().asString());
	}

	@Test
	public void votacao() {
		
		String url = "https://api.thecatapi.com/v1/votes";
		String body = "{\"image_id\": \"cko\", \"value\": \"true\", \"sub_id\": \"demo-8341ad\"}";

		// given - passagem do header e body
		Response result = given().contentType("application/json").body(body).
		
		// when
		when().post(url);
		
		//then
		result.then().statusCode(200).body("message", containsString("SUCCESS"));

		System.out.println("resultado votacao: " + result.body().asString());
		
		//buscar do id do response
		String id = result.jsonPath().getString("id");
		votoId = id;
		System.out.println("ID => : " + id);
	}
	
	
	
	
	@Test
	public void deletaVotacao() {
		
		
		votacao();
		deletaVoto();
		
		
	
	}
	
	@Test
	public void deletaVoto() {
		
		String url = "https://api.thecatapi.com/v1/votes/{votoId}";
		
		//given
			Response res = 
					given()
					.contentType("application/json")
					.header("x-api-key", "2df9e004-3646-4616-b2c4-b218d94d5ad3")
					.pathParam("votoId", votoId)
		//when
					.when().delete(url);
			
			System.out.println("resultado do delete: " + res.body().asString());
			
		//then
			res.then().statusCode(200).body("message", containsString("SUCCESS"));
		
	}
	
	
}









