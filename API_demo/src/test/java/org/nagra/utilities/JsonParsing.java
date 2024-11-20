package org.nagra.utilities;

import io.restassured.response.Response;
import java.util.List;

public class JsonParsing {

    // Method with default index value
    public static String doParsing(Response res, String jsonPath) {
        return doParsing(res, jsonPath, 0); // Default index is 0
    }

    // Method with specified index value
    public static String doParsing(Response res, String jsonPath, int index) {
        
        	
    	Object responseValue = res.jsonPath().get(jsonPath);
        
        if (responseValue == null) {
        	String msj = res.jsonPath().getString("error.message");	//{"error":{"code":"200-002","message":"Resource Not Found"}}
        	return msj;	//"Resource Not Found"
        }

        if (responseValue instanceof List) {
            // If the response value is a list, get the element at the specified index
            List<?> responseList = (List<?>) responseValue;
            if (index >= 0 && index < responseList.size()) {
                return responseList.get(index).toString();
            } else {
                return "Value not found"; // or handle out-of-bounds index as needed
            }
        } else {
            // If the response value is not a list, return it as a string
            return responseValue.toString();
        }
    }
}





