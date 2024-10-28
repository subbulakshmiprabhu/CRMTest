package com.crm.qa.listener;



import java.awt.AWTException;
import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.crm.qa.base.TestBase;
import com.crm.qa.util.TestUtil;

public class Listener extends TestUtil implements ITestListener {
	public void onTestStart(ITestResult result) {
	   System.out.println("onTestStart");
	  }

	
	  public void onTestSuccess(ITestResult result) {
		    // not implemented
		  System.out.println("onTestSuccess");
		  }
	  
	  public void onTestFailure(ITestResult result) {
		  System.out.println("onTestFailure");
		  
		  try {
			  captureScreenShot();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		    // not implemented
		  }
	  public void onTestSkipped(ITestResult result) {
		    // not implemented
		  
		  System.out.println("onTestSkipped");
		  }

	  public  void onStart(ITestContext context) {
		    // not implemented
		  
		  System.out.println("onStart");
		  }
	  public void onFinish(ITestContext context) {
		    // not implemented
		  
		  System.out.println("onfinish");
		  }

}

