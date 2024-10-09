package org.nagra.utilities;

import io.restassured.response.Response;

// input parameter --- Response object and the jsonPath
// purpose ? --it will extract from response based upon the jsonPath 
// output ---- it will return the value after extraction.
public class JsonParsing {

	public static String  doParsing(Response res, String jsonPath) {
		
		String responseValue = res.jsonPath().get(jsonPath);
		return responseValue;
	}
}
