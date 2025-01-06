package com.orangehrm.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.orangehrm.utils.Utilities;

public class Base {
	
	WebDriver driver;
	public Properties prop;
	public Properties propdata;

	public Base() throws IOException
	{
		FileInputStream fin=new FileInputStream(System.getProperty("user.dir")+"/src/main/java/com/orangehrm/config/config.properties");
		prop= new Properties();
		prop.load(fin);
		
		FileInputStream findata=new FileInputStream(System.getProperty("user.dir")+"/src/main/java/com/orangehrm/testdata/testdata.properties");
		propdata= new Properties();
		propdata.load(findata);
		
	}
	        
	public WebDriver initialiseDriverAndOpenApplicationLoginPage(String BrowserName)
	{
		
	if(BrowserName.equals("Chrome"))
	{	
	driver=new ChromeDriver();
	}
	else if(BrowserName.equals("Edge"))
	{
	driver=new EdgeDriver();
	}
	else if(BrowserName.equals("Firefox"))
	{
		driver=new FirefoxDriver();
	}
	driver.get(prop.getProperty("hrm.url") );
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.IMPLICITLY_WAIT_TIME));
	driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utilities.PAGE_LOAD_TIME));
	return driver;
	}
}
