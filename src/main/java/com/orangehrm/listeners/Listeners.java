package com.orangehrm.listeners;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.orangehrm.utils.ExtentReport;
import com.orangehrm.utils.Utilities;



public class Listeners implements ITestListener{

	ExtentReports extentreport;
	WebDriver driver;
	ExtentTest extent;
	
	@Override
	public void onStart(ITestContext context) {
		 extentreport = ExtentReport.generateExtentReport();
	}

	@Override
	public void onTestStart(ITestResult result) {
		extent = extentreport.createTest(result.getName());
		extent.log(Status.INFO, result.getName()+" Test execution started..");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		String testName = result.getName();
		extent.log(Status.PASS,testName+" got successfully executed");
	}

	@Override
	public void onFinish(ITestContext context) {
		extentreport.flush();
	}
	
	@Override
	public void onTestFailure(ITestResult result) {
		
		String testName = result.getName();
		
		try {
			 driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		
		String destinationScreenshotFile = Utilities.takeScreenshot(driver, testName);
		
		extent.addScreenCaptureFromPath(destinationScreenshotFile);
		extent.log(Status.INFO, result.getThrowable());
		extent.log(Status.FAIL,testName+" got Failed");
		
	}
	
	

}
