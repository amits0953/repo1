package com.actitime.generics;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

public class SeleniumLib
{
   WebDriver driver;
   
   
/*******************************************************CONSTRUCTOR*****************/
   public SeleniumLib(WebDriver driver) 
   {
	   this.driver=driver;
   }
   
/*************************************enter data****************************************************/  
   public void enterData(WebElement txtbx, String input)
   {
	  if(txtbx.getAttribute("value")!=null)	  
	  {
		  txtbx.clear();
		  txtbx.sendKeys(input);
	  }
	  else
	  {
		  txtbx.sendKeys(input);
	  }
   }
   
/****************************************click button*************************************************/
   
   public void clickbutton(WebElement button)
   {
	   if(button.getAttribute("type").equals("button")) 
	   {
		   button.click();
	   }
	   else if (button.getAttribute("type").equals("submit"))
		   
	   {
		   button.submit();
	   }
	
   }
/***************************************click checkbox***************************************************/
	   
	   public void clickCheckbox(WebElement checkbox)
	   
	   {
		   if(checkbox.isSelected())//true   
		   {
			   System.out.println("check box ix already slected");
		   }
		   else {
			   checkbox.click();
			   }
	   }
	   
/********************************************************select option by text******************************/
	   
	      public void selectoptionByText(WebElement dropdwn , String text)
	   {
		   Select sel = new Select(dropdwn);
		   sel.selectByVisibleText(text);
	   }
	
	   
/*********************************************************mouse over**************************************/
	   
	   public void mouseOver(WebElement element)
	   {
		   Actions act= new Actions(driver);
		   act.moveToElement(element).perform();
	   }
	   
/********************************************************************hard code*********************************/
	   public void iSleep(int secs)
	   
	   {
		   try 
		   {
			   Thread.sleep(secs*1000);
		   }
		   catch(InterruptedException e) {
			   e.printStackTrace();
		   }
	   }
	   
/**************************************explicit wait********************************************/
	   public void eWaitForVisible(WebElement element, int secs)
	   {
		   WebDriverWait wait = new WebDriverWait(driver, secs);
		   wait.until(ExpectedConditions.visibilityOf(element));
	   }
	   
/******************************verify expected and actual result************************/
	   
	   public void validate(String expected, String actual,String passesMsg)
	   {
		   Assert.assertEquals(actual, expected);
		   Reporter.log(passesMsg, true);
	   }
	   
/***************************************get Element Text**************************************/
	   public String getElementText(WebElement element)
	   {
		   String text = element.getText();
		   return text;
	   }
/*************************************get Title*****************************************/
	   
	   public String getPageTitle()
	   {
		   String title = driver.getTitle();
		   return title;
	   }
	 
	   
/**************************** verify element display*******************************/
	   
	   public void validateElementDisplayed(WebElement element, String passmsg)
	   {
		   Assert.assertTrue(element.isDisplayed());
		   Reporter.log(passmsg, true);
	   }
	   
/*********************************validate Datacontains**********************/
	   public void validateDtaContains(WebElement element, String data, String passedmsg)
	   {
		   Assert.assertTrue(element.getText().contains(data));
		   Reporter.log(passedmsg, true);
		  
	   }
	   
/*******************************************Select date*****************************************/
	   public void selectDate(WebElement element)
	   {
		   DateFormat sdf= new SimpleDateFormat("MM/dd/yyyy");
		   Date dat= new Date();
		   element.sendKeys(sdf.format(dat));
	   }
	   
		
/*************************************billpayment*******************************************************/
	  public void billingType(WebElement element, String type)
	  {
		  Select se= new Select(element);
		  if(type.equalsIgnoreCase("Billable"))
		  {
			  se.selectByValue("1");
		  }
		  else
		  {
			  se.selectByValue("2");
		  }
	  }
	   
/**************************************popup handle*************************/
	   
	   public void popHnandle()
	   {
		   driver.switchTo().alert().accept();
	   }
	   
	   
	   
	   
	   
}
