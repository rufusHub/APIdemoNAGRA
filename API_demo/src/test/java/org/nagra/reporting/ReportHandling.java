package org.nagra.reporting;

import com.relevantcodes.extentreports.ExtentReports;

public class ReportHandling {

	public static ExtentReports handleReport() {
		
		ExtentReports rep = new ExtentReports("C:\\Users\\bermudez\\OneDrive - Kudelski Group\\Documents\\API_demo\\Report_demo.html", false);
		return rep;
	}
}
