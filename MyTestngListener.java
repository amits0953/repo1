package com.actitime.generics;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.google.common.io.Files;

public class MyTestngListener implements ITestListener
{
	public static int startCount, passedCount, failedCount, skippedCount=0;

	@Override
	public void onTestStart(ITestResult result) 
	{
		startCount++;
		Reporter.log(result.getName()+"Scripts execution starts ", true);
		
	}

	@Override
	public void onTestSuccess(ITestResult result) 
	{
		passedCount++;
		Reporter.log(result.getName()+"script pass :)" , true);
		
	}

	@Override
	public void onTestFailure(ITestResult result) 
	{
		failedCount++;
		Reporter.log(result.getName()+" Script is fail :(", true);
		TakesScreenshot ts = (TakesScreenshot) BaseLib.driver;
		File scrfile = ts.getScreenshotAs(OutputType.FILE);
		File destFile= new File("./ScreenShots/"+result.getName()+".png");
		try {
			Files.copy(scrfile, destFile);// earlier it was filesutils.copy
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		Reporter.log("screen shot has been taken ", true);
	}

	@Override
	public void onTestSkipped(ITestResult result) 
	{
		skippedCount++;
		Reporter.log(result.getName()+"script skipped :(" , true);
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context)
	{
		Reporter.log("suite execution starts "+ new Date() , true);
		
	}

	@Override
	public void onFinish(ITestContext context)
	{
		
		Reporter.log("suite executions ends "+ new Date(), true);
		System.out.println("Total script executed : "+ startCount);
		System.out.println("total script passed : "+ passedCount);
		System.out.println("toatl script failed : "+ failedCount);
		System.err.println("total script skipped : "+ skippedCount);
	}

}
