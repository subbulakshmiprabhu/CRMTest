/*
 * @autor : Naveen Khunteta
 * 
 */
package com.crm.qa.listener;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.IReporter;
import org.testng.IResultMap;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.xml.XmlSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.crm.qa.base.TestBase;
import com.crm.qa.util.TestUtil;


public class ExtentReporterNG extends TestBase implements IReporter{
	
	
	public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites,
			String outputDirectory) {
		 extent = new ExtentReports();
		ExtentSparkReporter spark = new ExtentSparkReporter(outputDirectory + File.separator
				+ "Extent.html");
		extent.attachReporter(spark);
		for (ISuite suite : suites) {
			Map<String, ISuiteResult> result = suite.getResults();

			for (ISuiteResult r : result.values()) {
				ITestContext context = r.getTestContext();

				buildTestNodes(context.getPassedTests(),Status.PASS);
				buildTestNodes(context.getFailedTests(), Status.FAIL );
				buildTestNodes(context.getSkippedTests(), Status.SKIP);
			}
		}

		extent.flush();
		
	}
	
	private void buildTestNodes(IResultMap tests,  Status status) {
	

		if (tests.size() > 0) {
			for (ITestResult result : tests.getAllResults()) {
				test = extent.createTest(result.getMethod().getMethodName());

				//test.setStartedTime(getTime(result.getStartMillis()));
				//test.setEndedTime(getTime(result.getEndMillis()));

				for (String group : result.getMethod().getGroups())
					test.assignCategory(group);
				 if(result.getStatus()==result.FAILURE || result.getStatus()==result.SKIP) {
                     String screenshotPath=(String) 
  result.getAttribute("screenshotPath");
                     test.addScreenCaptureFromPath(screenshotPath, "Failed Test Screenshot");
                 } 
				if (result.getThrowable() != null) {
					test.log(status, result.getThrowable());
				} else {
					test.log(status, "Test " + status.toString().toLowerCase()
							+ "ed");
				}

				//extent.endTest(test);
			}
		}
	}

	private Date getTime(long millis) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(millis);
		return calendar.getTime();
	}

}