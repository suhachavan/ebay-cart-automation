package com.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtendReportManager {
	
	
	 private static ExtentReports extent;

	    public static ExtentReports getInstance() {
	        if (extent == null) {
	            ExtentSparkReporter spark = new ExtentSparkReporter("test-output/ExtentReport.html");
	            extent = new ExtentReports();
	            extent.attachReporter(spark);
	            extent.setSystemInfo("Tester", "Your Name");
	            extent.setSystemInfo("Environment", "QA");
	        }
	        return extent;
	    }

}
