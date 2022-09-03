package api.test;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndPoints;
import api.payload.UserPojo;
import api.utilities.DataProviders;
import io.restassured.response.Response;

//data driven tests
public class DDTests {
	
	@Test(priority=1, dataProvider ="Data",dataProviderClass=DataProviders.class )//need to specify which dataProvider is providing the data
	//the data provider is sending the data to the below method and below method should receive the data.
	//hence, this method takes parameters exactly as the excel sheet columns order
	public void testPostUser(String userID,String userName, String fname,String lname, String useremail,String pwd, String ph)

	{
		//connection to pojo  class and set the data
		UserPojo userPayload = new UserPojo();
		userPayload.setId(Integer.parseInt(userID)); //Converting string to int
		userPayload.setUsername(userName);
		userPayload.setFirstName(fname);  //Assigning values to the POJO class variables
		userPayload.setLastName(lname);
		userPayload.setEmail(useremail);
		userPayload.setPassword(pwd);
		userPayload.setPhone(ph);
		
		Response response= UserEndPoints.createUser(userPayload);
		AssertJUnit.assertEquals(response.getStatusCode(), 200);
	}
	
	
	//Want to delete the users that were created above using "username"
	@Test(priority=2,dataProvider="UserNames",dataProviderClass=DataProviders.class )//mention the class name that it is coming from
	public void testDeleteUserByName(String userName) {
		Response response=UserEndPoints.deleteUser(userName); 
		response.then().log().all();
		AssertJUnit.assertEquals(response.getStatusCode(),200);
		//response.then().log().body();
		
		
		
	}

}
