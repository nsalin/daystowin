package com.ddf.test;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.Architecture;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

import com.dff.configs.DriverManager;
import com.dff.configs.GlobalVariables;
import com.dff.listeners.TestListener;
import com.dff.reports.ReportManager;
import com.dff.utils.Directory;
import com.dff.utils.ExcelUtil;

@Listeners({TestListener.class})
public class TestBase implements GlobalVariables{
	protected WebDriverWait wait;
	WebDriver driver;

	@BeforeSuite
	public void configurations(ITestContext context) {
		Directory dir = new Directory();
		dir.clearFolder(DOWNLOAD_FOLDER);
		dir.clearFolder(SCREENSHOT_FOLDER);
		dir.clearFolder(ALLURE_RESULTS);
	}

	@BeforeMethod (alwaysRun = true)
	public void setup(Method method) throws MalformedURLException {
		WebDriverManager.chromedriver().setup();
		HashMap<String, Object> chromePrefs = new HashMap<String, Object>();  
		chromePrefs.put("download.default_directory", DOWNLOAD_FOLDER);  
		ChromeOptions options = new ChromeOptions();  
		options.setExperimentalOption("prefs", chromePrefs);
		options.addArguments("--headless"); // Run Chrome in headless mode
		options.addArguments("--disable-gpu");
		options.addArguments("--verbose");
		options.addArguments("--log-level=0");
		options.addArguments("--no-sandbox"); // Bypass OS security model, REQUIRED on Linux
		options.addArguments("--disable-dev-shm-usage"); // Overcome limited resource problems
		options.addArguments("--remote-allow-origins=*");  
		options.setAcceptInsecureCerts(true);
		if (RUN_ENVIRONMENT.equalsIgnoreCase("grid")) {
			// Use the GRID_URL from GlobalVariables
			URL gridUrl = new URL(GRID_URL); // Dynamically obtain Grid URL from configuration
			driver = new RemoteWebDriver(gridUrl, options);
		} else {
			// Local execution with ChromeDriver
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(options);
		}
		driver.get(BASE_URL);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT);
		wait = new WebDriverWait(driver, EXPLICIT_WAIT);
		DriverManager.getInstance().setDriver(driver);
		ReportManager.startTest(method.getName());
	}

	@AfterMethod (alwaysRun = true)
	public void tearDown() {
		DriverManager.getInstance().getDriver().quit();
	}

	public Object[][] getTestData(String className) throws Exception{		
		ExcelUtil excel = new ExcelUtil();
		excel.setExcelFile(DATA_FOLDER + WORKBOOK);		
		int testRow = excel.getNumberOfRows(className);
		Object[][] data = new Object[testRow-1][1];
		for(int i=1; i<testRow; i++) {
			data[i-1][0] = excel.getData(className, i);
		}
		return data;
	}

	protected void pause(int sec) {
		try {
			Thread.sleep(sec*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}	
}
