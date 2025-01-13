package com.orangehrm.testcases;

import java.io.IOException;
import java.time.Duration;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.orangehrm.base.Base;
import com.orangehrm.pages.LoginPage;
import com.orangehrm.pages.PIMEmployeeListPage;

public class PIMEmployeeListTestCases extends Base{

	public static final Logger log =org.apache.logging.log4j.LogManager.getLogger(Login.class);
	public WebDriver driver;
	PIMEmployeeListPage pimEmployeeListPage;
	LoginPage loginpage;
	String expectedMessageOnInvalidData="No Records Found";
	String actualmsg;
	
	public PIMEmployeeListTestCases() throws IOException {
		super();
		
		
	}
	
	@BeforeMethod
	public void setUp()
	{
		log.info("INFO: Settiing up driver properties....");			
		driver=initialiseDriverAndOpenApplicationLoginPage(prop.getProperty("hrm.browser.name"));
		pimEmployeeListPage=new PIMEmployeeListPage(driver);
		loginpage= new LoginPage(driver);
		loginpage.enterUserName(prop.getProperty("hrm.user.name"));		
		loginpage.enterPassword(prop.getProperty("hrm.password"));
		loginpage.clickLoginButton();
		log.info("clicking on PIM option");
		pimEmployeeListPage.clickOnPIMOption();
		
	}
	
	
	@AfterMethod
	public void tearDown()
	{
		log.info("INFO: Driver is closing.....");
		driver.quit();
	}
	
	@Test
	public void verifySearchWithValidEmpolyeeName()
	{
		pimEmployeeListPage.enterEmployeeName(pimEmployeeListPage.returnEmployeeName());
		pimEmployeeListPage.clickOnSearchButton();		
		String actualEmployeeNameAfterSearch=pimEmployeeListPage.returnEmployeeName();
		Assert.assertEquals(pimEmployeeListPage.returnEmployeeName(),actualEmployeeNameAfterSearch);
	}
	
	@Test
	public void verifySearchWithInvalidEmpolyeeName()
	{
		pimEmployeeListPage.enterEmployeeName("#$@#$");
		pimEmployeeListPage.clickOnSearchButton();		
		actualmsg=pimEmployeeListPage.returnNoRecordFoundmsg();
		Assert.assertTrue(actualmsg.equals(expectedMessageOnInvalidData));
	}
	
	@Test
	public void verifySearchWithValidEmployeeID()
	{
		pimEmployeeListPage.enterEmployeeID(pimEmployeeListPage.returnValidEmployeeID());
		pimEmployeeListPage.clickOnSearchButton();
		String actualEmployeeID=pimEmployeeListPage.returnValidEmployeeID();
		Assert.assertEquals(pimEmployeeListPage.returnValidEmployeeID(),actualEmployeeID);
		
	}
	
	@Test
	public void verifySearchWithInvalidEmpolyeeID()
	{
		pimEmployeeListPage.enterEmployeeID("#$@#$");
		pimEmployeeListPage.clickOnSearchButton();	
		actualmsg=pimEmployeeListPage.returnNoRecordFoundmsg();
		Assert.assertTrue(actualmsg.equals(expectedMessageOnInvalidData));
	}
	
	@Test
	public void verifySearchWithValidEmployeeStatus() throws InterruptedException
	{
	
		String expectedEmployeeStatus="Full-Time Permanent";
		System.out.println("locating and clicking on dropdown arrow....");
		pimEmployeeListPage.clickOnDropdownArrow("employeesStatus");		
		Thread.sleep(5000);
		
		System.out.println("entering value in dropdwon....");
		pimEmployeeListPage.selectDropdownValue(expectedEmployeeStatus);		
		Thread.sleep(5000);
		
		System.out.println("Clicing on search button....");
		pimEmployeeListPage.clickOnSearchButton();		
		Thread.sleep(5000);
		
		Assert.assertEquals(pimEmployeeListPage.returnEmployeeStatus(), expectedEmployeeStatus);
	}

	@Test
	public void verifySearchWithJobTitle() throws InterruptedException
	{
		String expectedJobTitle="Chief Financial Officer";
		System.out.println("locating and clicking on dropdown arrow....");
		pimEmployeeListPage.clickOnDropdownArrow("JobTitle");		
		Thread.sleep(5000);
		
		System.out.println("entering value in dropdwon....");
		pimEmployeeListPage.selectDropdownValue(expectedJobTitle);		
		Thread.sleep(5000);
		
		System.out.println("Clicing on search button....");
		pimEmployeeListPage.clickOnSearchButton();		
		Thread.sleep(5000);
		
		Assert.assertEquals(pimEmployeeListPage.returnEmployeeJobTitle(), expectedJobTitle);

	}
	
	@Test
	public void verifySearchWithSubUnit() throws InterruptedException
	{
		String expectedSubunit="Administration";
		System.out.println("locating and clicking on dropdown arrow....");
		pimEmployeeListPage.clickOnDropdownArrow("subUnit");		
		Thread.sleep(5000);
		
		System.out.println("entering value in dropdwon....");
		pimEmployeeListPage.selectDropdownValue(expectedSubunit);		
		Thread.sleep(5000);
		
		System.out.println("Clicing on search button....");
		pimEmployeeListPage.clickOnSearchButton();		
		Thread.sleep(5000);
		
		Assert.assertEquals(pimEmployeeListPage.returnEmployeeSubUnit(), expectedSubunit);
	}	
	
	@Test
	public void verifyResetButtonFunctionality() throws InterruptedException
	{
		pimEmployeeListPage.enterEmployeeName("abcdef");
		pimEmployeeListPage.enterEmployeeID("9876");
		Thread.sleep(4000);
		pimEmployeeListPage.clickOnResetButton();
		Assert.assertTrue(pimEmployeeListPage.returnEnteredEmployeeName().equals(""));
		Assert.assertTrue(pimEmployeeListPage.returnEnteredEmployeeID().equals(""));
	}
	
	@Test
	public void verifyAddEmployeeButtonFunctionality() throws InterruptedException
	{
		pimEmployeeListPage.clickOnAddButton();
		Thread.sleep(2000);
		String expectedHeading="Add Employee";
		Assert.assertTrue(pimEmployeeListPage.returnAddEmployeeHeading().equals(expectedHeading));
	}
	
	@Test
	public void verifyEditAndDeleteActionsAreAvailableForAllTheRecords()
	{
		Assert.assertTrue(pimEmployeeListPage.returnEditAndDeleteActionsAreDisplayedForAllRecords());
	}
	
	
}
