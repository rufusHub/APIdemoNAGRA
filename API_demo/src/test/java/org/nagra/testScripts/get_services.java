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

public class get_services {
	
	@Test(priority = 3, groups = {"full_test"})
	public void services_entel() throws IOException {
		
		//REPORT
		String test_name = "services_entel";
		ExtentReports report = ReportHandling.handleReport();
		ExtentTest tn_report = report.startTest(test_name);
		String report_ms1 = test_name + " test is getting PASSED. Satus OK, Data OK";
		String report_ms2 = test_name + " test is getting FAILED. Satus OK, Data NOK";
		String report_ms3 = test_name + " test is getting FAILED. Satus NOK";
		
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
		
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Accept", "application/json, text/plain, */*");
		headers.put("Authorization", "Bearer " + accessToken);
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("filter", filter);
		
		Properties prop = PropertiesHandle.loadProperties(props);
		
		
        //API CALL
        HTTPMethods http = new HTTPMethods(prop);
        Response response = http.getFilteredMethos(urikeyname, headers, params);
               
        //VALIDATION
        //status validation 
      	Object result_status = validateResponse.statusCodeVAlidate(expectedCode, response, test_name);   		
  		
  		if(result_status.equals(true)) {	
  			
  		    //data validation
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


//System.out.println(res.getBody().asString());
//System.out.println(res);
