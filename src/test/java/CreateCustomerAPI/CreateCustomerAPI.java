package CreateCustomerAPI;

import static io.restassured.RestAssured.given;

import java.util.Hashtable;

import BaseTest.TestBase;
import io.restassured.response.Response;

public class CreateCustomerAPI extends TestBase{
	
	public static Response PostRequestToCreateCustomerAPIWithValidSecretKey(Hashtable<String, String> data)
	{
	
	Response response = given().auth().basic(config.getProperty("vaildSecretKey"),"")
	.formParam("name", data.get("name")).formParam("email", data.get("email"))
	.post(config.getProperty("customerAPIEndpoint"));
	
	return response; 
	}
	
	public static Response PostRequestToCreateCustomerAPIWithInValidSecretKey(Hashtable<String, String> data)
	{
		Response response= given().auth().basic(config.getProperty("InvaildSecretKey"),"")
				.formParam("name", data.get("name")).formParam("email", data.get("name"))
				.post(config.getProperty("customerAPIEndpoint"));
		
		return response;
	}

}
