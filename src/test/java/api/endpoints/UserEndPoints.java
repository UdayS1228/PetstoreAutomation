package api.endpoints;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import api.payload.UserPojo;
import io.restassured.http.ContentType;
import io.restassured.response.Response;


//This is Userendpoints.java
//It is used to perform create, retrieve, update, delete requests the user Api's
//These are not test methods. test methods are part of only test case classes
//we are not creating test cases here but just end points
//these endpoints methods, we are calling from test case data, look at the framework diagram 


/*Steps:
	1. Taking the payload as an argument
	2. adding the pre-requisites like headers and body
	3. sending the url
	4. getting the response and storing the response in the variable
	5. returning the response variable
	*/

public class UserEndPoints {
	//Create user
	public static Response createUser(UserPojo payload)
	{
		Response response=
		given()
			.contentType(ContentType.JSON) // check for swagger doc of what to send along with the req
			.accept(ContentType.JSON)
			.body(payload)
			
		.when()
			.post(Routes.post_url); //it will return some response 
		return response;
			
	}
	//System.out.println("this is a test purpose: checking the local changes to commit")
	//Read User / get user
	public static Response readUser(String userName)
	{
		Response response=
		given()
			.pathParam("username", userName)
		.when()
			.get(Routes.get_url); //it will return some response 
		return response;
			
	}
	
	//Update User
	public static Response updateUser(String userName, UserPojo payload)
	{
		Response response=
		given()
			.contentType(ContentType.JSON) // check for swagger doc of what to send along with the req
			.accept(ContentType.JSON)
			.pathParam("username", "userName") //pathparam "username" must be same as pojo class variable username.
			.body(payload)
			
		.when()
			.put(Routes.update_url); //it will return some response 
		return response;
			
	}
	
	//delete User
	public static Response deleteUser(String userName)
	{
		Response response=
		given()
			.pathParam("username", userName)
		.when()
			.delete(Routes.delete_url); //it will return some response 
		return response;
			
	}
	//System.out.println("uday is practing commits");
	// Hi, i'm practicing the commits and want to see the changes in github repository
}
