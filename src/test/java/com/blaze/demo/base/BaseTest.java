package com.blaze.demo.base;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
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
		try {
			String process;

			Process p = Runtime.getRuntime().exec("tasklist.exe /fo csv /nh");
			BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
			while ((process = input.readLine()) != null) {
				System.out.println(process);
			}
			input.close();
			p.destroy();
		} catch (Exception err) {
			err.printStackTrace();
		}

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

	public void killBrowser(String browser) {

		Process process = null;

		try {
			if (browser.equals("chrome")) {

				process = Runtime.getRuntime().exec("taskkill /im chrome.exe /f");
				process = Runtime.getRuntime().exec("taskkill /im chromedriver.exe /f");
			} else if (browser.equals("firefox")) {

				process = Runtime.getRuntime().exec("taskkill /im firefox.exe /f");
				process = Runtime.getRuntime().exec("taskkill /im geckodriver.exe /f");
			} else if (browser.equals("ie")) {

				process = Runtime.getRuntime().exec("taskkill /im iexplore.exe /f");
				process = Runtime.getRuntime().exec("taskkill /im iedriver.exe /f");
			} else if (browser.equals("edge")) {

				process = Runtime.getRuntime().exec("taskkill /im msedge.exe /f");
				process = Runtime.getRuntime().exec("taskkill /im msedgedriver.exe /f");
			}
			else if(browser.equals("API")){
				
			}

		} catch (Exception err) {
			err.printStackTrace();
		}
		//process.destroy();
	}

	@Parameters("browser")
	@BeforeTest
	public void beforeTest(String browser) throws Exception {

		browserName = browser;

		killBrowser(browserName);
		WAIT_FOR_SEC(WAIT_2_SECS);

		if (browser.equals("chrome")) {

			WebDriverManager.chromedriver().setup();
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
			System.err.println("Invalid browser parameter");
		}
		
		
	}
	
	public void initialize(){
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.get("https://www.demoblaze.com/");
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
