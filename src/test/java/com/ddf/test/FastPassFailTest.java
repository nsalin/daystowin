package com.ddf.test;

import com.dff.configs.DriverManager;
import com.dff.listeners.TestListener;
import com.dff.reports.ReportManager;
import io.qameta.allure.Description;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

@Listeners({TestListener.class})
public class FastPassFailTest extends TestBase {

    @Override
    @BeforeMethod(alwaysRun = true)
    public void setup(Method method) {
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT);
        wait = new WebDriverWait(driver, EXPLICIT_WAIT);
        DriverManager.getInstance().setDriver(driver);
        ReportManager.startTest(method.getName());
    }

    @Test(testName = "Fast Passing Test", description = "Fast Suite")
    @Description("Checking and marking passing a test")
    public void fastPassTest(){
        assertTrue( true);
    }

    @Test(testName = "Fast Failing Test", description = "Fast Suite")
    @Description("Checking and marking failing a test")
    public void fastFailTest(){
        assertFalse(true);
    }
}
