package com.orangehrm.utils;

import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReport {

	public static ExtentReports generateExtentReport()
	{
		ExtentReports extentReport = new ExtentReports();
		File file = new File(System.getProperty("user.dir")+"/test-output/ExtentReport/extentreport.html");
		ExtentSparkReporter sparkReporter=new ExtentSparkReporter(file);
		sparkReporter.config().setReportName("OrangeHRM Automation Test Report");
		extentReport.attachReporter(sparkReporter);		
		return extentReport;
	}
	
}
