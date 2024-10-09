package org.nagra.assertions;

import org.nagra.reporting.logsCapture;

public class Assertion1 {

	public static Boolean assertion1(int expected, int actual, String className) {
		
		if(expected==actual) {
			logsCapture.takeLog(className, "status code is matching");
			return true;
			
		}else {
			logsCapture.takeLog(className, "status code is NOT matching");
			return false;
		}
	}
	
	public static Boolean assertion2(String expected, String actual, String className) {
		
		if(expected.equals(actual)) {
			logsCapture.takeLog(className, "Data is matching");
			return true;
			
		}else {
			logsCapture.takeLog(className, "Data is NOT matching");
			return false;
		}
	}
}
