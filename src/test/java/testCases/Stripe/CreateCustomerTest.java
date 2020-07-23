package testCases.Stripe;

import static io.restassured.RestAssured.*;

import java.util.Hashtable;

import org.testng.Assert;
import org.testng.annotations.Test;

import BaseTest.TestBase;
import CreateCustomerAPI.CreateCustomerAPI;
import io.restassured.response.Response;
import utilities.DataUtil;

public class CreateCustomerTest extends TestBase{
	
	@Test(dataProviderClass = DataUtil.class, dataProvider = "data")
	public void validateCreateCustomerAPIWithValidSecretKey(Hashtable<String, String> data)
	{
		
		Response response= CreateCustomerAPI.PostRequestToCreateCustomerAPIWithValidSecretKey(data);
		response.prettyPrint();
		System.out.println("status code is "+response.statusCode());
		Assert.assertEquals(response.statusCode(), 200);	
	}
	
	@Test(dataProviderClass = DataUtil.class, dataProvider = "data")
	public void validateCreateCustomerAPIWithInValidSecretKey(Hashtable<String, String> data)
	{
		
		Response response= CreateCustomerAPI.PostRequestToCreateCustomerAPIWithInValidSecretKey(data);
		response.prettyPrint();
		System.out.println("status code is "+response.statusCode());
		Assert.assertEquals(response.statusCode(), 200);
			
	}
	

}
