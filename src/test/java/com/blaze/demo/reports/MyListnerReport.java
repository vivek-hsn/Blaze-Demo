package com.blaze.demo.reports;


import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;	import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.blaze.demo.base.BaseTest;


public class MyListnerReport extends BaseTest implements ITestListener{
	
	static ExtentTest test = null;
	private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
	
	public void onStart(ITestContext context){
	}
		
	public void onTestStart(ITestResult result){
		
		test = extent.createTest(result.getMethod().getMethodName() + "-" + browserName);
		extentTest.set(test);
		
	}
	
	public void onTestSuccess(ITestResult result){
		
		extentTest.get().log(Status.PASS, result.getMethod().getMethodName() + " method on " + browserName + " is Passed");
	}
	
	public void onTestSkipped(ITestResult result){
		extentTest.get().log(Status.SKIP, result.getMethod().getMethodName() + " method on " + browserName + " is Skipped");
	}
	
	public void onTestFailure(ITestResult result){
		
		TakesScreenshot ts = (TakesScreenshot)driver;
		
		try{
			File src = ts.getScreenshotAs(OutputType.FILE);
			String path = System.getProperty("user.dir") + "\\results\\" + result.getMethod().getMethodName() + "-" + browserName +".png";
			FileUtils.copyFile(src, new File(path));
			extentTest.get().fail(result.getThrowable());
			extentTest.get().addScreenCaptureFromPath(path, result.getMethod().getMethodName() + "-" +browserName);
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public void onTestFailedButWithinSuccessPercentage(ITestResult result){
	}
	
	public void onFinish(ITestContext context){
	}

}
