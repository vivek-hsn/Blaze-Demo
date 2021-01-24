package com.blaze.demo.base;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	protected static final int WAIT_1_SEC = 1;
	protected static final int WAIT_2_SECS = 2;
	protected static final int WAIT_3_SECS = 3;
	protected static final int WAIT_4_SECS = 4;
	protected static final int WAIT_5_SECS = 5;
	protected static final int WAIT_10_SECS = 10;

	public static String browserName = "API";

	public static WebDriver driver = null; 

	public static ExtentReports extent = null;
	
	public Logger logger;

	@BeforeSuite
	public void beforeSuite() {

		String path = System.getProperty("user.dir") + "\\results\\reports.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Web Automation Result");
		reporter.config().setDocumentTitle("Test Results");
		extent = new ExtentReports();
		extent.attachReporter(reporter);
	}

	public static void WAIT_FOR_SEC(int sec) {
		try {
			Thread.sleep(sec * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Parameters("browser")
	@BeforeTest
	public void beforeTest(String browser) {

		if (browser.equals("chrome")) {
			
			System.out.println("Chrome is starting for execution");
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--no-sandbox");
			options.addArguments("--disable-dev-shm-usage");
			options.addArguments("--headless");
			driver = new ChromeDriver();
			initialize();

		} else if (browser.equals("firefox")) {

			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			initialize();

		} else if (browser.equals("ie")) {

			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
			initialize();

		} else if (browser.equals("edge")) {

			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			initialize();

		} else if (browser.equals("API")){
			logger = Logger.getLogger("RestAssuredFrameWork");
		}
		else {
			System.out.println("Invalid browser parameter");
		}
		browserName = browser;
	}
	
	public void initialize(){
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.get("https://www.demoblaze.com/");
		System.out.println("URL is entered");
	}

	@BeforeMethod
	public void beforeMethod() {
	}

	@AfterMethod
	public void afterMethod() {

	}

	@AfterClass
	public void afterClass() {

	}

	@AfterTest()
	public void afterTest() {
		driver.quit();
	}

	@AfterSuite()
	public void flushReport() {
		extent.flush();
	}
	
	@DataProvider(name="OrderDetails")
    public Object[][] getDataFromDataprovider(){
    return new Object[][] 
    	{
            { "Vivek HS", "India", "Bengaluru", "4811 1111 1111 1114", "Dec", "2021"},
            { "", "", "", "", "", ""}
        };

    }

}
