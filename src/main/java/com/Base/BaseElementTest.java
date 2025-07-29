package com.Base;

import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.utils.ConfigReader;
import com.utils.ExtendReportManager;
import com.utils.Report;
 
public class BaseElementTest {
	 protected WebDriver driver;
	 protected Properties config;
	 protected ExtentReports extent;
	 protected ExtentTest test;
	@BeforeTest
	public void SetUp() {
		
		 config = ConfigReader.loadProperties();
	    driver = new ChromeDriver();   

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
	    driver.get(config.getProperty("Url"));
		
	}
	
	 @BeforeClass
	    public void setupReport() {
	        extent = ExtendReportManager.getInstance();
	    }
	
	
	 @AfterTest
	    public void tearDown() {
	        if (driver != null) {
	            driver.quit();
	        }
	    }
	 
	 @AfterClass
	 public void tearDownReport() {
	     if (extent != null) {
	         extent.flush();   
	     }
	 }


}
