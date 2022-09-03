package api.test;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.AssertJUnit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.endpoints.UserEndPoints2;
import api.payload.UserPojo;
import io.restassured.response.Response;

public class UserTests2 {
	Faker faker;
	UserPojo userPayload;
	
	public Logger logger; //its class in log4j and logger variable
	
	@BeforeClass   //this method will execute before starting all the test methods
	public void setup() {
		//in this method we will generate the data using faker class.
		// ill pass this data to POJO class
		faker = new Faker(); //obj creation
		userPayload = new UserPojo(); // whatever the data we prepare using Faker class, the same data we need to pass to the POJO class
		userPayload.setId(faker.idNumber().hashCode()); //it randomly generates some id number 
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5, 10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		
		
		//logs
		logger=LogManager.getLogger(this.getClass()); // initiating the logger variable using getlogger method
	
	}
	@Test(priority =1)
	public void test_PostUser()
	{
		logger.info("************Creating user****************");
		
		Response response= UserEndPoints2.createUser(userPayload); //calling static method using classname directly and passing argument
		response.then().log().all(); // we can use then() here. because, after getting response, on top of it, we can do validations
		AssertJUnit.assertEquals(response.getStatusCode(), 200);
		
		logger.info("************ user is created ****************");
	}
	@Test(priority =2)
	public void test_getUserByName() 
	{
		logger.info("************Reading user****************");
		Response response=UserEndPoints2.readUser(this.userPayload.getUsername()); //why this keyword?
		
		response.then().log().all();
		//response.then().log().body();
		//String R1=response.asString();
		//R1.
		
		AssertJUnit.assertEquals(response.getStatusCode(),200); // when use assertion, use get keyword; otherwise, response.statuscode() would be fine
		//can add more validations like json response,json validations.
		logger.info("************user info is displayed****************");
		
		
	}
	//Update data using payload
	@Test(priority=3)
	public void test_updateUserByName() 
	{
		
		logger.info("************updating user****************");
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		
		Response response = UserEndPoints2.updateUser(this.userPayload.getUsername(), userPayload);//why this keyword
		response.then().log().all();
		//response.then().log().body();
		AssertJUnit.assertEquals(response.getStatusCode(), 200);
		
		//cheking data after updation
		
		Response responseAfterUpdate=UserEndPoints2.readUser(this.userPayload.getUsername()); //why this keyword?
		response.then().log().all();
		//response.then().log().body();
		AssertJUnit.assertEquals(responseAfterUpdate.getStatusCode(),200); 
		
		//we can do alot of validation using response
		logger.info("************user is updated****************");
	}
	// delete user by name
	@Test(priority=4)
	public void test_deleteUserByName() {
		logger.info("************deleting user****************");
		Response response=UserEndPoints2.deleteUser(this.userPayload.getUsername()); //why this keyword?
		response.then().log().all();
		AssertJUnit.assertEquals(response.getStatusCode(),200);
		//response.then().log().body();
		logger.info("************user deleted****************");
		
	}
	
}
















