package org.nagra.testScripts;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.nagra.reporting.ReportHandling;
import org.nagra.responseValidation.validateResponse;
import org.nagra.testSteps.HTTPMethods;
import org.nagra.utilities.PropertiesHandle;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.restassured.response.Response;

public class get_user_capabilities {

	@Test(priority = 2, groups = {"smoke_test", "full_test"})
	public void user_capabilities_entel() throws IOException {
	
		//REPORT
		String test_name = "capabilities_entel";
		ExtentReports report = ReportHandling.handleReport();
		ExtentTest tn_report = report.startTest(test_name);
		String report_ms1 = test_name + " test is getting PASSED. Satus OK, Data OK";
		String report_ms2 = test_name + " test is getting FAILED. Satus OK, Data NOK";
		String report_ms3 = test_name + " test is getting FAILED. Satus NOK";
		
		//VALUES
		String props = "../API_demo/URI.properties";
		String urikeyname = "Entel_capabilities";
		Integer expectedCode = 200;
		String expectedData = "4";
		String jsonPathToValidate = "totalRecords";
		
		//get token
		token t = new token();
		String accessToken = t.token_entel();
		 
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Authorization", "Bearer " + accessToken);
		
		Properties prop = PropertiesHandle.loadProperties(props);
		
	    //API CALL
        HTTPMethods http = new HTTPMethods(prop);
        Response response = http.getAlltMethod(urikeyname, headers);
        
        
        //VALIDATION 
        //status validation.
        Object result_status = validateResponse.statusCodeVAlidate(expectedCode, response, test_name);
  		
 		if(result_status.equals(true)) {	
  			
  			//data validation.
  			Object result_data = validateResponse.dataValidate(expectedData, response, jsonPathToValidate, test_name);
  			
  			if(result_data.equals(true)) {
  				tn_report.log(LogStatus.PASS, report_ms1);
  				report.endTest(tn_report);
  				report.flush();
  			}
  			else {
  				tn_report.log(LogStatus.FAIL, report_ms2);
  				report.endTest(tn_report);
  				report.flush();
  			}
  		}
  		else {
  			tn_report.log(LogStatus.FAIL, report_ms3);
  			report.endTest(tn_report);
  			report.flush();
  		}
	}
	
}
