package com.orangehrm.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PIMEmployeeListPage {

WebDriver driver;
	
	@FindBy(xpath = "//span[text()='PIM']")
	private WebElement PIMOption;
	
	@FindBy(xpath="(//div[@class='oxd-table-card'])[1]/div/child::div[3]")
	private WebElement validEmployeeName;
	
	@FindBy(xpath="(//div[@class='oxd-table-card'])[1]/div/child::div[2]")
	private WebElement validEmployeeID;
	
	@FindBy(xpath = "(//input[@placeholder='Type for hints...'])[1]")
	private WebElement employeeName;
	
	@FindBy(xpath = "//button[@type='submit']")
	private WebElement btnSearch;
	
	@FindBy(xpath="//span[text()='No Records Found']")
	private WebElement noRecordFoundMsg; 
	
	@FindBy(xpath="//input[@class='oxd-input oxd-input--active' and not(@placeholder='Search')]")
	private WebElement employeeID;
	
	@FindBy(xpath="(//i[@class='oxd-icon bi-caret-down-fill oxd-select-text--arrow'])[1]")
	private WebElement employeeStatusdropdownArrow;
	
	@FindBy(xpath="(//i[@class='oxd-icon bi-caret-down-fill oxd-select-text--arrow'])[2]")
	private WebElement includedropdownArrow;
	
	@FindBy(xpath="(//i[@class='oxd-icon bi-caret-down-fill oxd-select-text--arrow'])[3]")
	private WebElement jobTitledropdownArrow;
	
	@FindBy(xpath="(//i[@class='oxd-icon bi-caret-down-fill oxd-select-text--arrow'])[4]")
	private WebElement subUnitdropdownArrow;
	
	@FindBy(xpath="//div[@role='option']")
	private List<WebElement> dropDownOptions;
	
	@FindBy(xpath="(//div[@class='oxd-table-card'])[1]/div/div[6]/div")
	private WebElement employeeStatus;
	
	@FindBy(xpath="(//div[@class='oxd-table-card'])[1]/div/div[5]/div")
	private WebElement employeeJobTitle;
	

	@FindBy(xpath="(//div[@class='oxd-table-card'])[1]/div/div[7]/div")
	private WebElement employeeSubUnit;
	
	public PIMEmployeeListPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	
	public void enterEmployeeName(String name)
	{
		
		employeeName.sendKeys(name);
		
	}
	
	public String returnEmployeeName()
	{
		String expectedEmployeeName = validEmployeeName.getText();
		return expectedEmployeeName;
	}
	
	
	public void clickOnPIMOption()
	{
		PIMOption.click();
	}
	
	public void clickOnSearchButton()
	{
		btnSearch.click();
		
	}	
	
	public String returnNoRecordFoundmsg()
	{
		return noRecordFoundMsg.getText();
	}
	
	public String returnValidEmployeeID()
	{
		return validEmployeeID.getText();
	}
	
	public void enterEmployeeID(String employeeid)
	{
		employeeID.sendKeys(employeeid);
	}
	
	
	public void clickOnDropdownArrow(String dropdownName)
	{
		if(dropdownName.equals("employeesStatus")) {
			employeeStatusdropdownArrow.click();
		}
		else if(dropdownName.equals("include"))
		{
			includedropdownArrow.click();
		}
		else if(dropdownName.equals("JobTitle")) {
			jobTitledropdownArrow.click();
			}
		else if(dropdownName.equals("subUnit")) {
			subUnitdropdownArrow.click();
		}

			
	}
	
	public void selectDropdownValue(String value)
	{
		
		try{
		for (WebElement webElement : dropDownOptions) {
			if(webElement.getText().equals(value)) {
				webElement.click();
			}
		}
		}catch(Exception e)
		{
			PageFactory.initElements(driver, this);
			for (WebElement webElement : dropDownOptions) {
				if(webElement.getText().equals(value)) {
					webElement.click();
				}
			}
		}
		
	}
	
	public String returnEmployeeStatus()
	{
		return employeeStatus.getText();
	}
	
	public String returnEmployeeJobTitle()
	{
		return employeeJobTitle.getText();
	}
	
	public String returnEmployeeSubUnit()
	{
		return employeeSubUnit.getText();
	}
}

