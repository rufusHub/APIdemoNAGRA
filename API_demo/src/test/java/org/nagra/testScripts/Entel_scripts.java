package org.nagra.testScripts;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.nagra.utilities.PropertiesHandle;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.restassured.response.Response;

import org.nagra.reporting.ReportHandling;
import org.nagra.testSteps.HTTPMethods;
import org.nagra.utilities.JsonHandle;



public class Entel_scripts {
	
	ExtentReports report = ReportHandling.handleReport();
	ExtentTest te_report;

	@Test(priority = 1)
	public void token_entel() throws IOException {
		
		//REPORT
		String test_name = "token_entel";
		te_report = report.startTest(test_name);
		
		//VALUES
		String props = "../API_demo/URI.properties";
		String body = "../API_demo/src/test/java/org/nagra/resources/entel.json";
		String urikeyname = "Entel_token";
		String tenantId = "nagra";
		Integer expectedCode = 200;
		
		//get headers
		Map<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/json");
        headers.put("nv-tenant-id", tenantId);
        
        //get properties
        Properties prop = PropertiesHandle.loadProperties(props);
        String requestBody = JsonHandle.readJsonData(body);
        
        
        //API CALL
        HTTPMethods http = new HTTPMethods(prop);
        Response response = http.postMethod(requestBody, urikeyname, headers);
        
        
        //VALIDATION.
		validation val = new validation();
		val.post_validation(te_report, test_name, prop, urikeyname, headers, requestBody, expectedCode, response);
	}
	
	
	
	@Test(priority = 2)
	public void services_entel() throws IOException {
		
		//REPORT
		String test_name = "services_entel";
		te_report = report.startTest(test_name);
		
		
		//VALUES
		String props = "../API_demo/URI.properties";
		String urikeyname = "Entel_services";
		String filter = "{\"locale\":\"es_LA\"}";
		Integer expectedCode = 200;
		String expectedData = "es_LA";
		String jsonPathToValidate = "services.locale";
		
		//get token
		token t = new token();
		String accessToken = t.token_entel();
		
		//get headers
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Accept", "application/json, text/plain, */*");
		headers.put("Authorization", "Bearer " + accessToken);
		
		//get parameters
		Map<String, String> params = new HashMap<String, String>();
		params.put("filter", filter);
		
		//get properties
		Properties prop = PropertiesHandle.loadProperties(props);
		
		
        //API CALL
        HTTPMethods http = new HTTPMethods(prop);
        Response response = http.getFilteredMethos(urikeyname, headers, params);
       
        
        //VALIDATION
		validation val = new validation(); 
		String res = val.get_validation(te_report, test_name, expectedData, prop, response, urikeyname, expectedCode, jsonPathToValidate, headers, params);
	}
	
	
	
	@Test(priority = 3)
	public void user_capabilities_entel() throws IOException {
		
		//REPORT
		String test_name = "user_capabilities_entel";
		te_report = report.startTest(test_name);
	
		
		//VALUES
		String props = "../API_demo/URI.properties";
		String urikeyname = "Entel_capabilities";
		Integer expectedCode = 200;
		String expectedData = "4";
		String jsonPathToValidate = "totalRecords";
		
		//get token
		token t = new token();
		String accessToken = t.token_entel();
		 
		//get headers
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Authorization", "Bearer " + accessToken);
		
		//get properties
		Properties prop = PropertiesHandle.loadProperties(props);
		
		
	    //API CALL
        HTTPMethods http = new HTTPMethods(prop);
        Response response = http.getAlltMethod(urikeyname, headers);
        
        
		//VALIDATION.
		validation val = new validation();
		String res = val.get_validation(te_report, test_name, expectedData, prop, response, urikeyname, expectedCode, jsonPathToValidate, headers);    
	}
	
	
	
	@Test(priority = 4)
	public void programes_entel() throws IOException {
		
		//REPORT
		String test_name = "programes_entel";
		te_report = report.startTest(test_name);
		
		//VALUES
		String props = "../API_demo/URI.properties";
		String urikeyname = "Entel_programes";
		Integer expectedCode = 200;
		String expectedData = "GLOBAL_10241_20241106122800";
		String jsonPathToValidate = "programmes.id";
		
			
		//get token
		token t = new token();
		String accessToken = t.token_entel();
		
		//get headers
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Authorization", "Bearer " + accessToken);
		
		//get properties
		Properties prop = PropertiesHandle.loadProperties(props);
		
		
	    //API CALL
        HTTPMethods http = new HTTPMethods(prop);
        Response response = http.getAlltMethod(urikeyname, headers);

        
        //VALIDATION.
		validation val = new validation();
		String res = val.get_validation(te_report, test_name, expectedData, prop, response, urikeyname, expectedCode, jsonPathToValidate, headers);    
	
	}
	
	
	@AfterMethod
    public void afterMethod(ITestResult result) {
        long duration = result.getEndMillis() - result.getStartMillis();
        te_report.log(LogStatus.INFO,"Execution time: " + duration + " milliseconds");
    }
	
	 @AfterSuite
	 public void tearDown() {
		report.endTest(te_report);
		report.flush();
	 }
	
	
	
	
}
