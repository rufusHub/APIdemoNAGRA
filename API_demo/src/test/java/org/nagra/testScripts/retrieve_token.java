package org.nagra.testScripts;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.nagra.utilities.PropertiesHandle;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.nagra.reporting.ReportHandling;
import org.nagra.responseValidation.validateResponse;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.restassured.response.Response;

import org.nagra.testSteps.HTTPMethods;
import org.nagra.utilities.JsonHandle;


public class retrieve_token {
	
	public void token_netlife() throws IOException {
		
		//Report
		String test_name = "token_netlife";
		ExtentReports report = ReportHandling.handleReport();
		ExtentTest tn_report = report.startTest(test_name);
		
		//Values
		String props = "../API_demo/URI.properties";
		String body = "../API_demo/src/test/java/org/nagra/resources/netlife.json";
		String urikeyname = "Netlife_token";
		String tenantId = "NETLIFE";
		
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/json");
        headers.put("nv-tenant-id", tenantId);
		
		
		Properties prop = PropertiesHandle.loadProperties(props);
		String requestBody = JsonHandle.readJsonData(body);
		
		//API call
		HTTPMethods http = new HTTPMethods(prop);
		Response res = http.postMethod(requestBody, urikeyname, headers);
			
		//Validation
		Object result = validateResponse.statusCodeVAlidate(200, res, test_name );
		if(result.equals(true)) {
			tn_report.log(LogStatus.PASS, "POST test 'token_netlife' is getting PASS");
			report.endTest(tn_report);
			report.flush();
			
		}
		
		else {
			tn_report.log(LogStatus.FAIL, "'POST test 'token_netlife' is getting FAIL");
			report.endTest(tn_report);
			report.flush();
			//SoftAssert.assertEquals(result, true, "Assertion failed...");	// Soft assertion - code continue.
			//SoftAssert.assertAll();
		}	
	}
	
	
	
	@Test(priority = 1, groups = {"smoke_test", "full_test"})
	public void token_entel() throws IOException {
		
		//Report
		String test_name = "token_entel";
		ExtentReports report = ReportHandling.handleReport();
		ExtentTest te_report = report.startTest(test_name);
		String report_ms1 = test_name + " test is getting PASSED";
		String report_ms2 = test_name +	" test is getting FAILED";	
		
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
        
        //Validation
        Object result_status = validateResponse.statusCodeVAlidate(expectedCode, response, test_name );
        
		if(result_status.equals(true)) {
			te_report.log(LogStatus.PASS, report_ms1);
			report.endTest(te_report);
			report.flush();
	
		}
		
		else {
			te_report.log(LogStatus.FAIL, report_ms2);
			report.endTest(te_report);
			report.flush();

		}
	}
}
