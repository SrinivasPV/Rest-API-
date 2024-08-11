package stepdefinition;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.*;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.internal.common.assertion.Assertion;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.*;

import java.io.FileNotFoundException;

import API.APIResource;
import Pojo.DeleteBook;
import Resource.TestData;
import Resource.Utils;
import dev.failsafe.internal.util.Assert;

public class Book {
	
	public static TestData td = new TestData();
	public static RequestSpecification body;
	public static ResponseSpecification responseSpec;
	public static Response response;
	public static String id;
	
	@Given("User get the body for adding book")
	public void user_get_the_body_for_adding_book() throws FileNotFoundException {
	    // Write code here that turns the phrase above into concrete actions
	    body = given().spec(Utils.reqSpecBuilder()).body(td.addBookBody());
	   
	     responseSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
	}

	@When("^calling (.+) to add the book using (.+)$")
	public Response calling_add_book_api_to_add_the_book(String resource, String method) {
	    // Write code here that turns the phrase above into concrete actions
	    APIResource api = APIResource.valueOf(resource);
	    String apiRes = api.getAPIResource();
	    if(method.equalsIgnoreCase("POST"))
	    {
	    	 response = body.when().post(apiRes).then().spec(responseSpec).extract().response();
	    }
	    else if(method.equalsIgnoreCase("GET"))
	    {
	    	 response = body.when().get(apiRes).then().spec(responseSpec).extract().response();
	    }
	    
	    return response;
	}

	@Then("^Checking the status code (.+)$")
	public void checking_the_status_code(int status) {
	    // Write code here that turns the phrase above into concrete actions
	    int statusCode = response.getStatusCode();
	    org.junit.Assert.assertEquals(statusCode, status);
	    System.out.println(response.asString());
	}

	@Then("^Verifying the message (.+)$")
	public void verifying_the_message_successfully_added(String msg) {
	    // Write code here that turns the phrase above into concrete actions
		if(msg.contains("added"))
		{
	    String msgs = Utils.getJasonPath(response.asString(),"Msg");
	    org.junit.Assert.assertEquals(msgs, msg);
		}
		else if(msg.contains("deleted"))
		{
			String msgs = Utils.getJasonPath(response.asString(), "msg");
			org.junit.Assert.assertEquals(msgs, msg);
		}
			
	}
	
	@And("{string} book details using {string}")
	public void getBookDetails(String resource,String method) throws FileNotFoundException {
		id = Utils.getJasonPath(response.asString(), "ID");
		body = given().spec(Utils.reqSpecBuilder()).queryParam("ID", id);
		response = calling_add_book_api_to_add_the_book(resource, method);
		int statusCode = response.getStatusCode();
		org.junit.Assert.assertEquals(statusCode, 200);
		String getResponse = response.asString();
		System.out.println(getResponse);
		
	}
	
	@Given("User get the body for deleting the book")
	public static void get_body_for_deleteing_book() throws FileNotFoundException
	{
		DeleteBook d = td.deleteBookBody(id);
		body = given().spec(Utils.reqSpecBuilder()).body("{\r\n"
				+ " \r\n"
				+ "\"ID\" : \""+id+"\"\r\n"
				+ " \r\n"
				+ "} \r\n"
				+ "");
//		body = given().spec(Utils.reqSpecBuilder()).body(id);
	}

}
