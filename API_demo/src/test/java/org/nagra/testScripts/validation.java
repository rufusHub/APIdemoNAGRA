package org.nagra.testScripts;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.nagra.reporting.ReportHandling;
import org.nagra.responseValidation.validateResponse;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.restassured.response.Response;

public class validation {

	public void post_validation(String test_name, Properties prop, String urikeyname, Map<String, String> headers, String requestBody,
			                    Integer expectedCode, Response response        ) {
		
		ExtentReports report = ReportHandling.handleReport();
		ExtentTest te_report = report.startTest(test_name);
		
		String report_ms1 = test_name + " test is getting PASSED";
		String report_ms2 = test_name +	" test is getting FAILED";	
		
        //Validation
        // Logging request details
        te_report.log(LogStatus.INFO, "Request URI: " + prop.getProperty(urikeyname));
        te_report.log(LogStatus.INFO, "Request Headers: " + headers.toString());
        te_report.log(LogStatus.INFO, "Request Body: " + requestBody);
        
        Object result_status = validateResponse.statusCodeVAlidate(expectedCode, response, test_name );
        
		if(result_status.equals(true)) {
			te_report.log(LogStatus.PASS, "Response Code: " + response.getStatusCode());
            te_report.log(LogStatus.PASS, "Response Body: " + response.getBody().asString());
			te_report.log(LogStatus.PASS, report_ms1);	
		}
		
		else {
			te_report.log(LogStatus.FAIL, "Response Code: " + response.getStatusCode());
            te_report.log(LogStatus.FAIL, "Response Body: " + response.getBody().asString());
            te_report.log(LogStatus.FAIL, report_ms2);
		}
		report.endTest(te_report);
		report.flush();
	}
	
	
	
	public String get_validation(String test_name, String expectedData, Properties prop, Response response, String urikeyname,
            Integer expectedCode, String jsonPathToValidate, Map<String, String> headers) {
		
		Map<String, String> params = new HashMap<>();
		
		return get_validation(test_name, expectedData, prop, response, urikeyname, expectedCode, jsonPathToValidate, headers, params );
		
	}
	
	
	
	public String get_validation(String test_name, String expectedData, Properties prop, Response response, String urikeyname,
			                   Integer expectedCode, String jsonPathToValidate, Map<String, String> headers, Map<String, String> params) {
		
		ExtentReports report = ReportHandling.handleReport();
		ExtentTest te_report = report.startTest(test_name);
		
		String report_ms1 = test_name + " test is getting PASSED. Satus OK, Data OK";
		String report_ms2 = test_name + " test is getting FAILED. Satus OK, Data NOK - '" + expectedData + "' not found in the body.";
		String report_ms3 = test_name + " test is getting FAILED. Satus NOK";
		
		te_report.log(LogStatus.INFO, "Request URI: " + prop.getProperty(urikeyname));
        te_report.log(LogStatus.INFO, "Request Headers: " + headers.toString());
        te_report.log(LogStatus.INFO, "Request Parameters: " + params.toString());
               
        //status validation 
      	Object result_status = validateResponse.statusCodeVAlidate(expectedCode, response, test_name);   		
  		
  		if(result_status.equals(true)) {	
  			
  		    //data validation
  			Object result_data = validateResponse.dataValidate(expectedData, response, jsonPathToValidate, test_name);
  			if(result_data.equals(true)) {
  				te_report.log(LogStatus.PASS, "Response Code: " + response.getStatusCode());
  	            te_report.log(LogStatus.PASS, "Response Body: " + response.getBody().asString());
  				te_report.log(LogStatus.PASS, report_ms1);
  				
  			}
  			else {
  				te_report.log(LogStatus.FAIL, "Response Body: " + response.getBody().asString());
  				te_report.log(LogStatus.FAIL, report_ms2);
  			}
  		}
  		else {
  			te_report.log(LogStatus.FAIL, "Response Code: " + response.getStatusCode());
  			te_report.log(LogStatus.FAIL, report_ms3);
  			
  		}
		report.endTest(te_report);
		report.flush();
		
		return "Validation done";
		
	}
}
