package com.neotech.steps;

import com.neotech.utils.APIConstants;

// API, Lesson 06, Part-1, 22:00
// Documentation: https://neo-api.azurewebsites.net/swagger/index.html

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static org.hamcrest.Matchers.*;


public class GetOneClassSteps {
	
	// Make instance variable, common methods
	RequestSpecification request; // Hover the mouse to "given".
	Response response; // Hover the mouse to ".get"
	
	
	@Given("I create a GET request")
	public void i_create_a_get_request() {
		RestAssured.baseURI = APIConstants.BASE_URI;
		request = RestAssured.given();
		
	}
	@Given("I provide the classId {int} as path param")
	public void i_provide_the_class_id_as_path_param(Integer classId) {
		request.pathParam("Id", classId);
		// "Id" comes from the documentation.
	    
	}
	@When("I send the GET request to GetOneClass endpoint")
	public void i_send_the_get_request_to_get_one_class_endpoint() {
		response = request.when().get(APIConstants.GET_ONE_CLASS_ENDPOINT);
		response.prettyPeek();
		// ".get" = from the documentation
		// "prettyPeak" = print on console
	    
	}
	// Validation should be happened after get response from the server.
	@Then("I validate the status code is {int}")
	public void i_validate_the_status_code_is(Integer statusCode) {
		response.then().assertThat().statusCode(statusCode);
			    
	}
	@Then("I validate that the Id in response body is {int}")
	public void i_validate_that_the_id_in_response_body_is(Integer id) {
		response.then().assertThat().body("result.id", equalTo(id));
		
		// resut.id = run on the documentation with the id mentioned in the feature file.
		// The "id" will be displayed under "result" so "result.id"
		// Need to import "hamcrest Matcheres". Must be "static".
	   
	}
	@Then("I validate that the class term is {string}")
	public void i_validate_that_the_class_term_is(String term) {
		response.then().assertThat().body("result.term", equalTo(term));
		
	    
	}




}
