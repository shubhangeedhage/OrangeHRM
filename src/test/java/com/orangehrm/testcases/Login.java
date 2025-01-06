package com.orangehrm.testcases;

import java.io.IOException;
import java.util.logging.LogManager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.orangehrm.base.Base;
import com.orangehrm.pages.LoginPage;
import com.orangehrm.utils.Utilities;

import org.apache.logging.log4j.Logger; 

public class Login extends Base{
	
	
	public static final Logger log =org.apache.logging.log4j.LogManager.getLogger(Login.class);
	public WebDriver driver;
	Object[][] loginData;
	LoginPage loginpage;
	
	public Login() throws IOException {
		
		super();
	}

	
	
	@BeforeMethod
	public void setUp()
	{
		log.info("INFO: Settiing up driver properties....");			
		driver=initialiseDriverAndOpenApplicationLoginPage(prop.getProperty("hrm.browser.name"));
		loginpage=new LoginPage(driver);
		
	}
	
	
	@AfterMethod
	public void tearDown()
	{
		log.info("INFO: Driver is closing.....");
		driver.quit();
	}
	
	@DataProvider
	public Object[][] usernamepassworddataprovider()
	{
		loginData=Utilities.readDataFromExcelFile("./src/main/java/com/orangehrm/testdata/orangeHrmLoginTestData.XLSX", "Login");
		return loginData;
	}

	@Test(priority=1)
	public void verifyLoginWithValidCredential()
	{	
		
		loginpage.enterUserName(prop.getProperty("hrm.user.name"));		
		loginpage.enterPassword(prop.getProperty("hrm.password"));
		loginpage.clickLoginButton();
		Assert.assertTrue(loginpage.dashboardHeadingIsDisplayed());
	}
	
	@Test(priority=2,dataProvider="usernamepassworddataprovider")
	public void verifyLoginWithInvalidCredential(String userName,String Password)
	{
		log.info("Using Dataprovider");
		loginpage.enterUserName(userName);		
		loginpage.enterPassword(Password);
		loginpage.clickLoginButton();
		Assert.assertTrue(loginpage.invalidCredentialsWarningMessageIsDispalyed());
	}
	
		
	@Test(priority=3)
	public void verifyLoginWithValidUsernameAndInvalidPassword()
	{
		loginpage.enterUserName(prop.getProperty("hrm.user.name"));
		loginpage.enterPassword(propdata.getProperty("invalid.password"));
		loginpage.clickLoginButton();
		Assert.assertTrue(loginpage.invalidCredentialsWarningMessageIsDispalyed());
	}
	
	@Test(priority=4)
	public void verifyLoginWithInvalidUsernameAndValidPassword()
	{
		loginpage.enterUserName(propdata.getProperty("invalid.username"));	
		loginpage.enterPassword(prop.getProperty("hrm.password"));
		loginpage.clickLoginButton();
		Assert.assertTrue(loginpage.invalidCredentialsWarningMessageIsDispalyed());
	}
	
	@Test(priority=5)
	public void verifyLoginWithBlankUsernameAndPassword()
	{
		loginpage.clickLoginButton();
		Assert.assertTrue(loginpage.requiredFieldsValidationMessageDispalyed());
	}

}
