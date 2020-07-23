package testCases.Stripe;

import java.util.Hashtable;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import BaseTest.TestBase;
import CreateCustomerAPI.DeleteCustomerAPI;
import io.restassured.response.Response;
import listeners.ExtentListeners;
import utilities.DataUtil;
import utilities.TestUtil;

public class DeleteCustomerTest extends TestBase {
	
	@Test(dataProviderClass = DataUtil.class, dataProvider = "data")
	public void DeleteCustomerWIthID(Hashtable<String, String> data)
	{
		
		Response response= DeleteCustomerAPI.RequestToDeleteCustomerAPIWithValidID(data);
		ExtentListeners.testReport.get().info(data.toString());
		response.prettyPrint();
		System.out.println("status code is "+response.statusCode());
		
		/*String actualID= response.jsonPath().get("id");
		Assert.assertEquals(actualID, data.get("id"),"Id did not match");
		Assert.assertEquals(response.statusCode(), 200);*/
		
		
		//more assertions: get the id deleted customer using json class and print it match is the execl ID 
		//match with deleted customer
		
		System.out.println("Presence check for Object Key : "+TestUtil.jsonHasKey(response.asString(), "object"));
		System.out.println("Presence check for Deleted Key : "+TestUtil.jsonHasKey(response.asString(), "deleted"));
		Assert.assertTrue(TestUtil.jsonHasKey(response.asString(), "id"),"ID key is not present in the JSON response");
		
		String actual_id = TestUtil.getJsonKeyValue(response.asString(), "id");
		System.out.println(actual_id);
		Assert.assertEquals(actual_id, data.get("id"),"ID not matching");
		
		System.out.println("Object key value is : "+TestUtil.getJsonKeyValue(response.asString(), "object"));
		System.out.println("Deleted key value is : "+TestUtil.getJsonKeyValue(response.asString(), "deleted"));
		
		
		Assert.assertEquals(response.statusCode(), 200);

		
		
		
		
		
			
	}
	

}
