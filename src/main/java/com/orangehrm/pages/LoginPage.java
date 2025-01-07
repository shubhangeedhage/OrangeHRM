package com.orangehrm.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
		
	WebDriver driver;
	
	@FindBy(xpath="//input[@name='username']")
	private WebElement Username;
	
	@FindBy(xpath="//input[@name='password']")
	private WebElement Password;
	
	@FindBy(xpath="//button[@class='oxd-button oxd-button--medium oxd-button--main orangehrm-login-button']")
	private WebElement LoginBtn;
	
	@FindBy(xpath="//h6[text()='Dashboard']")
	private WebElement DashboardHeading;
	
	@FindBy(xpath="//p[text()='Invalid credentials']")
	private WebElement InvalidCredentialWarningMessage;
	
	@FindBy(xpath="//span[text()='Required']")
	private WebElement RequiredFieldsValidationMessage;
	
	public LoginPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void enterUserName(String username)
	{
		Username.sendKeys(username);
	}
	
	public void enterPassword(String password)
	{
		Password.sendKeys(password);
	}
	
	public void clickLoginButton()
	{
		LoginBtn.click();
	}
	
	public boolean dashboardHeadingIsDisplayed()
	{
		boolean dashHeading=DashboardHeading.isDisplayed();
		return dashHeading;
	}
	
	public boolean invalidCredentialsWarningMessageIsDispalyed()
	{
		boolean warningMessage=InvalidCredentialWarningMessage.isDisplayed();
		return warningMessage;
	}
	
	public boolean requiredFieldsValidationMessageDispalyed()
	{
		boolean fieldValidationMessage=RequiredFieldsValidationMessage.isDisplayed();
		return fieldValidationMessage;
	}
	
	
}
