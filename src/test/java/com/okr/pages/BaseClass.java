package com.okr.pages;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.okr.utility.BrowserFactory;
import com.okr.utility.ConfigDataProvider;
import com.okr.utility.Helper;

public class BaseClass {
	
		public static WebDriver driver;
		public ConfigDataProvider config;
		public ExtentTest logger;
		public ExtentReports report;
		public ExtentSparkReporter extent;

		@BeforeSuite
		public void setUpSuite() {
			Reporter.log("Setting up the report and test is ready", true);
			config = new ConfigDataProvider();
			extent = new ExtentSparkReporter(new File(System.getProperty("user.dir")+"/Reports/ofs_"+Helper.getCurrentDateTime()+".html"));
			report = new ExtentReports();
			report.attachReporter(extent);
			Reporter.log("Setting Done and test started", true);
		}

		@BeforeClass
		public void setup()
		{
			Reporter.log("Trying to start browser and getting application ready", true);
			config = new ConfigDataProvider();
			driver = BrowserFactory.start_application(config.getbrowser(), config.URL());
			logger = report.createTest(getClass().getSimpleName());
			Reporter.log("Browser and application is up and running", true);
		}

		@AfterMethod
		public void tearDownMethod(ITestResult result) throws IOException {
			logger = report.createTest(getClass().getSimpleName());
			Reporter.log("Test is about to end", true);

			if (result.getStatus() == ITestResult.FAILURE) {
				logger.fail("TEST FAILURE",
						MediaEntityBuilder.createScreenCaptureFromPath(Helper.captureScreenshot(driver)).build());
			} else if (result.getStatus() == ITestResult.SUCCESS) {
				logger.pass("TEST SUCCESS",
						MediaEntityBuilder.createScreenCaptureFromPath(Helper.captureScreenshot(driver)).build());
			} else if (result.getStatus() == ITestResult.SKIP) {
				logger.skip("TEST SKIPPED",
						MediaEntityBuilder.createScreenCaptureFromPath(Helper.captureScreenshot(driver)).build());
			}

			report.flush();

			Reporter.log("Test Completed >>> Report Generated", true);

			// Quit the browser after each test method
			//BrowesrFactory.quitBrowser(driver);
		}
}
