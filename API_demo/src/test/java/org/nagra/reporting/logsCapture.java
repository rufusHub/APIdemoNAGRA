package org.nagra.reporting;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;


public class logsCapture {

	public static void takeLog(String className, String msg) {
		DOMConfigurator.configure("../API_demo/layout.xml");
		Logger L = Logger.getLogger(className);
		L.info(msg);
		
	}
}
