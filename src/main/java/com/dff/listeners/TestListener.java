package com.dff.listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.lang.reflect.Method;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;

import com.aventstack.extentreports.Status;
import com.dff.configs.GlobalVariables;
import com.dff.reports.ReportManager;
import com.dff.utils.Log;
import com.dff.utils.ScreenshotUtil;

public class TestListener implements ITestListener, GlobalVariables {
    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final String jiraUrl = "https://innovationdays.atlassian.net"; // Example: "https://yourcompany.atlassian.net"
    private final String apiToken = "9f5d1ea6-131d-4ddc-a273-e50872ecd3e4"; // Your Jira API token
    private final String projectKey = "TES"; // Example: "PROJ"

    private void updateZephyrTestCase(ITestResult result, String status) {
        Method method = result.getMethod().getConstructorOrMethod().getMethod();
        if (method.isAnnotationPresent(ZephyrTestCase.class)) {
            String testCaseKey = method.getAnnotation(ZephyrTestCase.class).value();
            try {
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(new URI(jiraUrl + "/rest/atm/1.0/testcase/" + testCaseKey + "/testresult"))
                        .header("Authorization", "Bearer " + apiToken)
                        .header("Content-Type", "application/json")
                        .POST(HttpRequest.BodyPublishers.ofString(
                                "{\"status\":\"" + status + "\", \"projectKey\":\"" + projectKey + "\"}"))
                        .build();
                HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

                System.out.println("Test case " + testCaseKey + " updated with status " + status);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

	private static String getTestName(ITestResult result) {
        return result.getMethod().getMethodName();
    }   
	
    public void onFinish(ITestContext iTestContext) {
        //Do tier down operations for ExtentReports reporting!
        ReportManager.endTest();
    }
    
	
    public void onTestStart(ITestResult iTestResult) {
    	Log.messageLog(getTestName(iTestResult)+" "+START);
    }
    
	
    public void onTestSuccess(ITestResult iTestResult) {
    	Log.messageLog(getTestName(iTestResult)+" "+PASS);
        //ExtentReports log operation for passed tests.
        updateZephyrTestCase(iTestResult, "Pass");
        ReportManager.getTest().log(Status.PASS,  getTestName(iTestResult)+" "+PASS);
    }
    
	
    public void onTestFailure(ITestResult iTestResult) {
    	Log.error(getTestName(iTestResult)+" "+FAIL,  iTestResult.getThrowable());
    	Log.messageLog(getTestName(iTestResult)+" "+FAIL);
    	ScreenshotUtil.attachScreenshotToReport(iTestResult, Status.FAIL);
        ScreenshotUtil.takeScreenShot(getTestName(iTestResult)+"_"+FAIL);
        ScreenshotUtil.attachImageToAllure(getTestName(iTestResult));
        Log.attachLogToAllure(iTestResult.getThrowable());
        updateZephyrTestCase(iTestResult, "FAIL");
    }
    	
    public void onTestSkipped(ITestResult iTestResult) {
        Log.messageLog(getTestName(iTestResult)+" "+SKIP);
        updateZephyrTestCase(iTestResult, "Skip");
        //ExtentReports log operation for skipped tests.
        ReportManager.getTest().log(Status.SKIP, getTestName(iTestResult)+" "+SKIP);

    }    
}