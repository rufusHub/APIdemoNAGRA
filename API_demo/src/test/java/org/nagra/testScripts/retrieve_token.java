package org.nagra.testScripts;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.nagra.utilities.PropertiesHandle;
import org.testng.asserts.SoftAssert;
import org.nagra.reporting.ReportHandling;
import org.nagra.responseValidation.validateResponse;


import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.restassured.response.Response;

import org.nagra.testSteps.HTTPMethods;
import org.nagra.utilities.JsonHandle;


public class retrieve_token {
	
	public void token() throws IOException {
		
		ExtentReports report = ReportHandling.handleReport();
		ExtentTest tc1 = report.startTest("retrieve_token");
		
		
		String props = "../API_demo/URI.properties";
		String body = "../API_demo/src/test/java/org/nagra/resources/netlife.json";
		String urikeyname = "Netlife_token";
		String tenantId = "NETLIFE";
		
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/json");
        headers.put("nv-tenant-id", tenantId);
		
		
		Properties prop = PropertiesHandle.loadProperties(props);
		
		String requestBody = JsonHandle.readJsonData(body);
		
		HTTPMethods http = new HTTPMethods(prop);
		
		Response res = http.postMethod(requestBody, urikeyname, headers);
		System.out.println("Response data is "+ res.asString());
		
		Object result = validateResponse.statusCodeVAlidate(200, res, "retrieve_token" );
		if(result.equals(true)) {
			tc1.log(LogStatus.PASS, "TestCase1 is getting pass");
			report.endTest(tc1);
			report.flush();
		}
		else {
			tc1.log(LogStatus.FAIL, "TestCase1 is getting fail");
			report.endTest(tc1);
			report.flush();
			//SoftAssert.assertEquals(result, true, "Assertion failed...");	// Soft assertion - code continue.
			//SoftAssert.assertAll();
		}
		
	}

}
