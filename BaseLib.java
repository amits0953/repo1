package com.actitime.generics;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

public class BaseLib 
{
  public static WebDriver driver;
  
  
  @BeforeMethod
  @Parameters({"browser" , "baserurl"})
  public static void preCondition(String browserName , String url)
  {
	 if(browserName.equalsIgnoreCase("Chrome"))
	 {
		 System.setProperty("webdriver.chrome.driver", "./exefiles/chromedriver.exe");
		 driver= new ChromeDriver();
		 Reporter.log("chrome is launched", true);
	 }
	 
	 else if(browserName.equalsIgnoreCase("firefox"))
	 {
		 System.setProperty("webdriver.gecko.driver", "./exefiles/geckodriver.exe");
		 driver= new FirefoxDriver();
		 Reporter.log("fire fox launched", true);
	 }
	 
	 else if(browserName.equalsIgnoreCase("ie"))
		 
	 {
		 System.setProperty("webdriver.ie.driver", "./exefiles/IEDriverServer.exe");
		 driver= new InternetExplorerDriver();
		 Reporter.log("ie launched " ,true);
	 }
	 
	 driver.manage().window().maximize();
	 driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	 driver.navigate().to(url);
	 Reporter.log(url +"url is navigated ", true);
  }
  
  @AfterMethod
  public void postCondition()
  {
	  driver.close();
	  Reporter.log("Browser closed", true);
	  if(driver!=null)	  
	  {
		  driver.quit();
		  Reporter.log("All session are closed ", true);
	  }
  }
  
  
  
  
}
