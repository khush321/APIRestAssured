package CreateCustomerAPI;

import static io.restassured.RestAssured.given;

import java.util.Hashtable;

import BaseTest.TestBase;
import io.restassured.response.Response;

public class DeleteCustomerAPI extends TestBase{
	
	
	public static Response RequestToDeleteCustomerAPIWithValidID(Hashtable<String, String> data)
	{
	
	Response response = given().auth().basic(config.getProperty("vaildSecretKey"),"")
	.delete(config.getProperty("customerAPIEndpoint")+"/"+data.get("id"));
	
	return response; 
	}
	

}
