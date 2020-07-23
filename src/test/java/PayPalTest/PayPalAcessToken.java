package PayPalTest;

import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.Test;

import BaseTest.TestBase;
import PayPalPojo.orders;
import PayPalPojo.purchaseUnits;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class PayPalAcessToken {
	
	static String username="AX9JOeTLbPIq4EeXyfsSD7tqWgfdQSAkeirMRfLvg0DGt3e-qe7KlUIgExAapGTA7-o86IAijwD1JWZ4";
	static String password ="ELw5gphPofbQblnQzmBB3OV4o1gcPjqbPbdwPCxrWvRweoGmRW1yXo2j3fT-fWidQv2wp19Yn-oolTnv";
	static String accessToken;
	static String orderId;
	
	@Test(priority=1)
	public void PayPalAccess_token()
	{
		
		RestAssured.baseURI="https://api.sandbox.paypal.com/";
		
		Response response = given()
				.param("grant_type","client_credentials")
				.auth().preemptive()
				.basic(username, password).post("/v1/oauth2/token");
		response.prettyPrint();
		 accessToken= response.jsonPath().get("access_token").toString();
		System.out.println("access token is: "+accessToken);
	}
	
	@Test(priority=2, dependsOnMethods="PayPalAccess_token" )
	public void createOrder()
	{
		ArrayList<purchaseUnits> list = new ArrayList<purchaseUnits>();
		list.add(new purchaseUnits("USD","500.00"));
		orders order= new orders("CAPTURE",list);
		/*String jsonBody= "{\r\n" + 
				"  \"intent\": \"CAPTURE\",\r\n" + 
				"  \"purchase_units\": [\r\n" + 
				"    {\r\n" + 
				"      \"amount\": {\r\n" + 
				"        \"currency_code\": \"USD\",\r\n" + 
				"        \"value\": \"100.00\"\r\n" + 
				"      }\r\n" + 
				"    }\r\n" + 
				"  ]\r\n" + 
				"}";*/
		RestAssured.baseURI="https://api.sandbox.paypal.com/";
		Response response = given().contentType(ContentType.JSON).auth().oauth2(accessToken)
				.body(order)
				.post("/v2/checkout/orders");
		response.prettyPrint();
		orderId= response.jsonPath().get("id").toString();
		System.out.println("orderID is :"+orderId);
		Assert.assertEquals(response.jsonPath().get("status").toString(), "CREATED");
	}
	
	@Test(priority=3, dependsOnMethods= {"PayPalAccess_token","createOrder"})
	public void getOrderDetails()
	{
		RestAssured.baseURI="https://api.sandbox.paypal.com/";
		
		Response response = given()
				.contentType(ContentType.JSON).auth().oauth2(accessToken)
				.get("/v2/checkout/orders"+"/"+orderId);
		response.prettyPrint();
		
	}
	
	

}
