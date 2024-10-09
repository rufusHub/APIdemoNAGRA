package org.nagra.responseValidation;

import org.nagra.assertions.Assertion1;
import org.nagra.utilities.JsonParsing;

import io.restassured.response.Response;

public class validateResponse {

	public static Boolean statusCodeVAlidate(int expectedStatusCode, Response res, String className) {
		
		int actualStatusCode = res.statusCode();
		Boolean result = Assertion1.assertion1(expectedStatusCode, actualStatusCode, className);
		return result;
	}
	
	public static Boolean dataValidate(String expectedData, Response res, String jsonPath, String className) {
		
		String actualValue = JsonParsing.doParsing(res,	 jsonPath);
		Boolean result = Assertion1.assertion2(expectedData, actualValue, className);
		return result; 
	}
	
}
