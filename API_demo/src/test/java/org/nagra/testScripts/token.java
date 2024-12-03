package org.nagra.testScripts;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.nagra.testSteps.HTTPMethods;
import org.nagra.utilities.JsonHandle;
import org.nagra.utilities.PropertiesHandle;
import org.testng.annotations.BeforeTest;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.response.Response;

public class token {
	
//	private String accessTokenEntel;
//	private String accessTokenNetlife;
	
	public String token_entel() throws IOException {
		//Values
				String props = "../API_demo/URI.properties";
				String body = "../API_demo/src/test/java/org/nagra/resources/entel.json";
				String urikeyname = "Entel_token";
				String tenantId = "nagra";
				Integer expectedCode = 200;
				
				Map<String, String> headers = new HashMap<String, String>();
		        headers.put("Content-Type", "application/json");
		        headers.put("nv-tenant-id", tenantId);
		        
		        Properties prop = PropertiesHandle.loadProperties(props);
		        String requestBody = JsonHandle.readJsonData(body);
		        
		        //API call
		        HTTPMethods http = new HTTPMethods(prop);
		        Response response = http.postMethod(requestBody, urikeyname, headers);
		        
		        
				ObjectMapper objectMapper = new ObjectMapper();
				JsonNode jsonNode = objectMapper.readTree(response.asString());
				String accessTokenEntel = jsonNode.get("access_token").asText();
				System.out.println(accessTokenEntel);
				
				return accessTokenEntel;
				
	}
	
//	public String getAccessTokenEntel() {
//		return accessTokenEntel;
//	}
	
//	public String getAccessTokenNetlife() {
//		return accessTokenNetlife;
//	}
}
