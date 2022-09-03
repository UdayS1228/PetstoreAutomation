package api.endpoints;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.ResourceBundle;

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

	//method created for getting url's from properties file
	public class UserEndPoints2 {
	//we need to create an additional method which will load the properties file
		public static ResourceBundle getURL(){
			ResourceBundle routes = ResourceBundle.getBundle("routes");// ResourceBundle is the class name and routes is variable
					//the above method will load the properties file.// parameter "routes" is a property file name// no need to specify entire path here.it will go into resource and find it
			return routes;
		}
	
	//Create user
	public static Response createUser(UserPojo payload)
	{
		String post_url =getURL().getString("post_url");
		
		
		
		Response response=
		given()
			.contentType(ContentType.JSON) // check for swagger doc of what to send along with the req
			.accept(ContentType.JSON)
			.body(payload)
			
		.when()
			.post(post_url); //it will return some response 
		return response;
			
	}
	//Read User / get user
	public static Response readUser(String userName)
	{
		String get_url =getURL().getString("get_url");
		Response response=
		given()
			.pathParam("username", userName)
		.when()
			.get(get_url); //it will return some response 
		return response;
			
	}
	
	//Update User
	public static Response updateUser(String userName, UserPojo payload)
	{
		String update_url =getURL().getString("update_url");
		Response response=
		given()
			.contentType(ContentType.JSON) // check for swagger doc of what to send along with the req
			.accept(ContentType.JSON)
			.pathParam("username", "userName") //pathparam "username" must be same as pojo class variable username.
			.body(payload)
			
		.when()
			.put(update_url); //it will return some response 
		return response;
			
	}
	
	//delete User
	public static Response deleteUser(String userName)
	{
		String delete_url =getURL().getString("delete_url");
		Response response=
		given()
			.pathParam("username", userName)
		.when()
			.delete(delete_url); //it will return some response 
		return response;
			
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
