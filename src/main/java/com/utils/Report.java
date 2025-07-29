package com.utils;
 
	
 

	import com.aventstack.extentreports.ExtentTest;
	import com.aventstack.extentreports.Status;

	public class Report {
	    public static ExtentTest test;

	    public static void log(String message, Status status) {
	        if (test != null) {
	            test.log(status, message);
	        } else {
	            System.out.println("[WARN] Report not initialized. Logging to console: " + message);
	        }
	    }
	}


 
