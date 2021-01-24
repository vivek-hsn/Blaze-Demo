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
		System.out.println("On the start");

		
	}
		
	public void onTestStart(ITestResult result){
		
		System.out.println("On Test Start");
		test = extent.createTest(result.getMethod().getMethodName() + "-" + browserName);
		extentTest.set(test);
		
	}
	
	public void onTestSuccess(ITestResult result){
		
		System.out.println("On Test Success");
		extentTest.get().log(Status.PASS, result.getMethod().getMethodName() + " method on " + browserName + " is Passed");
	}
	
	public void onTestSkipped(ITestResult result){
		System.out.println("On test skipped");
		extentTest.get().log(Status.SKIP, result.getMethod().getMethodName() + " method on " + browserName + " is Skipped");
	}
	
	public void onTestFailure(ITestResult result){
		
		System.out.println("On test failure");
		
		TakesScreenshot ts = (TakesScreenshot)driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "\\results\\" + result.getMethod().getMethodName() + "-" + browserName +".png";
		try{
			FileUtils.copyFile(src, new File(path));
			extentTest.get().fail(result.getThrowable());
			extentTest.get().addScreenCaptureFromPath(path, result.getMethod().getMethodName() + "-" +browserName);
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public void onTestFailedButWithinSuccessPercentage(ITestResult result){
		System.out.println("On test Failed but with Success Percentage");
	}
	
	public void onFinish(ITestContext context){
		System.out.println("On Finish");
	}

}
