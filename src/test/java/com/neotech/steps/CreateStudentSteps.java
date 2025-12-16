package com.neotech.steps;

import org.json.JSONObject;

import com.neotech.utils.APIConstants;
import com.neotech.utils.APIGlobalVariables;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class CreateStudentSteps {
	// API Lesson 06, Part-3, 9:00
	
	int id;
	Response response;
	
	@When("I create a new student and store its id")
	public void i_create_a_new_student_and_store_its_id() {
		RestAssured.baseURI = APIConstants.BASE_URI;
		
		// Need to add JSON object on POM library before using JSONObject
		String payload = new JSONObject()
				.put("firstName", APIGlobalVariables.firstName)
				.put("lastName", APIGlobalVariables.lastName)
				.put("email", APIGlobalVariables.email)
				.put("city", APIGlobalVariables.city)
				.put("state", APIGlobalVariables.state)
				.put("studentNumber", APIGlobalVariables.studentNumber)
				.toString();
		
		id = RestAssured.given().auth().oauth2(APIGlobalVariables.token)
				.body(payload)
				.contentType(ContentType.JSON)
				.when().post(APIConstants.CREATE_STUDENT_ENDPOINT)
				.prettyPeek().body().jsonPath().getInt("result.id");
	   
	}
	@When("I get the student by the stored id")
	public void i_get_the_student_by_the_stored_id() {
		RestAssured.baseURI = APIConstants.BASE_URI;
		
		response = RestAssured.given().auth().oauth2(APIGlobalVariables.token)
				.queryParam("id", id)
				.when().get(APIConstants.GET_ONE_STUDENT_ENDPOINT)
				.prettyPeek();		
	    
	}
	@Then("I validate that information of the created student")
	public void i_validate_that_information_of_the_created_student() {
		response.then().assertThat()
		
	    
	}


}
