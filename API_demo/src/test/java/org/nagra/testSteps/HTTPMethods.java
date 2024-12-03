package org.nagra.testSteps;

import static io.restassured.RestAssured.*;

import java.util.Map;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class HTTPMethods {
	
	Properties pr;
	
	public HTTPMethods(Properties pr) {
		this.pr = pr;
	}
	
	public Response postMethod(String jsonBody, String uriKeyName, Map<String, String> headers) {
		
		String uriValue = pr.getProperty(uriKeyName);
		Response res =
		given()
//		.contentType(ContentType.JSON)
		.headers(headers)
		.body(jsonBody)
		.log().all()
		.when()
		.post(uriValue);
		
		System.out.println("'postMethod' Status code is " + res.statusCode());
		System.out.println("token is: "+res.asString());
		return res;
	}
	
	public Response postMethod(String uriKeyName, Map<String, String> headers) {
		
		String uriValue = pr.getProperty(uriKeyName);
		Response res =
		given()
		.headers(headers)
		.log().all()
		.when()
		.post(uriValue);
		
		System.out.println("'postMethod' Status code is " + res.statusCode());
		System.out.println("token is: "+res.asString());
		return res;
	}
	
	public Response getAlltMethod(String uriKeyName, Map<String, String> headers) {
		String uriValue = pr.getProperty(uriKeyName);
		Response res =
		given()
		.headers(headers)
		.log().all()
		.when()
		.get(uriValue);
		
		System.out.println("Status code: " + res.getStatusCode());
        System.out.println("Response body: " + res.getBody().asString());
		return res;
	}

	
	public Response getFilteredMethos(String uriKeyName, Map<String, String> headers, Map<String, String> params) {
		String uriValue = pr.getProperty(uriKeyName);
		Response res =
		given()
		.headers(headers)
		.queryParams(params)
		.log().all()
		.when()
		.get(uriValue);
		
		System.out.println("Status code: " + res.getStatusCode());
        System.out.println("Response body: " + res.getBody().asString());
		return res;
		
	}
	
	
	public Response getParticulartMethod(String uriKeyName, String endPointValue) {
		String uriValue = pr.getProperty(uriKeyName) + "/" + endPointValue;
		Response res =
		given()
		.contentType(ContentType.JSON)
		.when()
		.get(uriValue);
		
		System.out.println("'getParticulartMethod'Status code is " + res.statusCode());
		System.out.println("Response data is: ");
		System.out.println(res.asString());
		return res;
	}
	
	public Response putMethod(String jsonBody, String uriKeyName, String endPoint) {
		String uriValue = pr.getProperty(uriKeyName) + "/" + endPoint;
		Response res =
		given()
		.contentType(ContentType.JSON)
		.body(jsonBody)
		.when()
		.put(uriValue);
		
		System.out.println("'putMethod' Status code is " + res.statusCode());
		System.out.println("Response data is: ");
		System.out.println(res.asString());
		return res;
	}
	
	public void deleteMethod(String uriKeyName, String endPointValue) {
		String uriValue = pr.getProperty(uriKeyName) + "/" + endPointValue;
		Response res =
		given()
		.contentType(ContentType.JSON)
		.when()
		.delete(uriValue);
		
		System.out.println("'deleteMethod' Status code is " + res.statusCode());
		System.out.println("Response data is: ");
		System.out.println(res.asString());
	}
	
}
