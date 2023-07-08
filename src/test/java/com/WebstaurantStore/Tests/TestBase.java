package com.WebstaurantStore.Tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {

	public static WebDriver driver;
	public ExtentReports extent;
	public ExtentSparkReporter sparkReporter;
	public ExtentTest logger;
	static String url;
	static String browser;

		
	public static void readConfig() {
		try {
			InputStream input = new FileInputStream("config\\config.properties");
			Properties prop = new Properties();
			prop.load(input);
			url = prop.getProperty("url");
//			browser = prop.getProperty("browser");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

//	@BeforeTest
	public void generateReport() {
		sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + File.separator + "reports "
				+ File.separator + "WebstaurantStoreExtentReport.html");
		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		sparkReporter.config().setTheme(Theme.STANDARD);
		sparkReporter.config().setDocumentTitle("Automation Report");
		sparkReporter.config().setReportName("Automation Test Results by Tania Dash");

	}
	
	@Parameters("browser")
	@BeforeTest
	public void init(String browser, Method testMethod) {
//		logger = extent.createTest(testMethod.getName());
		readConfig();
		
		if (browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if (browser.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}		
		
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

	}



//	@AfterMethod
	public void afterMethod(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			logger.log(Status.FAIL, result.getThrowable());
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			logger.log(Status.PASS, result.getTestName());
		} else {
			logger.log(Status.SKIP, result.getTestName());
		}
	}

//	@AfterTest
	public void afterTest() {
		extent.flush();
	}

//	@AfterClass
//	public void tearDown() {
//		driver.close();
//		driver.quit();
//	}

}
