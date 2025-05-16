package com.InstagramBase;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.openqa.selenium.io.FileHandler;


import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class test implements ITestListener{
	@Override
	public void onStart(ITestContext context) {
	    System.out.println("On Test Start");
	 }

	  @Override
	  public void onTestFailure(ITestResult tr) {
		  System.out.println("On Test Failure");
		  
		  ITestContext context = tr.getTestContext();
		  WebDriver driver  = (WebDriver) context.getAttribute("driver");
		  
		  if(driver!=null) {
			  System.out.println("Driver found taking screenshot....");
			  takeScreenshot(driver,tr.getName());
		  }
		  else {
			  System.out.println("Driver is NULL in onTestFailure. Cannot take Screenshot");
		  }
	  }
	  public void takeScreenshot(WebDriver driver, String testName) {
		  try {
			  File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			  String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
			  String screenshotName = "screenshots/" + testName + "_" + timestamp + ".png";

			  new File("screenshots").mkdirs(); 
			  File directory = new File("screenshots");
			  if (!directory.exists()) {
			      boolean created = directory.mkdirs();
			      if (created) {
			          System.out.println("Directory 'screenshots' created successfully.");
			      } else {
			          System.out.println("Failed to create directory 'screenshots'.");
			      }
			  } 
			  else {
			      System.out.println("Directory 'screenshots' already exists.");
			  }

			  FileHandler.copy(screenshot, new File(screenshotName));
			  System.out.println("Screenshot saved at: " + screenshotName);
		  }
		  catch (IOException e) {
	    		e.printStackTrace();    	
	    	}
	  }
	  

	  @Override
	  public void onTestSkipped(ITestResult tr) {
		  System.out.println("On Test Skipped");
	  }

	  @Override
	  public void onTestSuccess(ITestResult tr) {
		  System.out.println("On Test Success");
	  }
	  @Override
	  public void onFinish(ITestContext context) {
		  System.out.println("On Test Finish");
	  }
	  
}
