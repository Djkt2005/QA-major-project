package com.Instagram.util;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import org.testng.IReporter;
import org.testng.IResultMap;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.ITestListener;
import org.testng.xml.XmlSuite;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.text.SimpleDateFormat;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class ExtentReporterNG implements IReporter, ITestListener {
    private ExtentReports extent;

    @Override
    public void onTestFailure(ITestResult result) {
        try {
            WebDriver driver = (WebDriver) result.getTestContext().getAttribute("driver");
            if (driver != null) {
                // Create timestamp for the screenshot name
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
                String timestamp = dateFormat.format(new Date());
                
                String screenshotPath = System.getProperty("user.dir") + "/screenshots/" + 
                    result.getName() + "_" + timestamp + ".png";
                
                File directory = new File(System.getProperty("user.dir") + "/screenshots/");
                if (!directory.exists()) {
                    directory.mkdir();
                }
                
                TakesScreenshot ts = (TakesScreenshot) driver;
                File source = ts.getScreenshotAs(OutputType.FILE);
                FileHandler.copy(source, new File(screenshotPath));
                System.out.println("Screenshot saved: " + screenshotPath);
                
                // Store the screenshot path in the test context for the reporter to use
                result.getTestContext().setAttribute("screenshotPath", screenshotPath);
            }
        } catch (Exception e) {
            System.out.println("Failed to capture screenshot: " + e.getMessage());
        }
    }

    @Override
    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(outputDirectory + "/ExtentReport.html");
        sparkReporter.config().setTheme(Theme.STANDARD);
        sparkReporter.config().setDocumentTitle("Automation Test Report");
        sparkReporter.config().setReportName("Test Report");

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);

        extent.setSystemInfo("Tester", "Krishant Tanti");
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("Browser", "Chrome");

        for (ISuite suite : suites) {
            Map<String, ISuiteResult> result = suite.getResults();

            for (ISuiteResult r : result.values()) {
                ITestContext context = r.getTestContext();
                buildTestNodes(context.getPassedTests(), Status.PASS);
                buildTestNodes(context.getFailedTests(), Status.FAIL);
                buildTestNodes(context.getSkippedTests(), Status.SKIP);
            }
        }
        extent.flush();
    }

    private void buildTestNodes(IResultMap tests, Status status) {
        if (tests.size() > 0) {
            for (ITestResult result : tests.getAllResults()) {
                ExtentTest test = extent.createTest(result.getMethod().getMethodName());

                for (String group : result.getMethod().getGroups()) {
                    test.assignCategory(group);
                }

                test.getModel().setStartTime(getTime(result.getStartMillis()));
                test.getModel().setEndTime(getTime(result.getEndMillis()));

                if (result.getThrowable() != null) {
                    test.log(status, result.getThrowable());

                    if (status == Status.FAIL) {
                        String screenshotPath = (String) result.getTestContext().getAttribute("screenshotPath");
                        if (screenshotPath != null && new File(screenshotPath).exists()) {
                            try {
                                test.addScreenCaptureFromPath(screenshotPath);
                                System.out.println("Screenshot added to report: " + screenshotPath);
                            } catch (Exception e) {
                                System.out.println("Failed to add screenshot to report: " + e.getMessage());
                            }
                        }
                    }
                } else {
                    test.log(status, "Test " + status.toString().toLowerCase() + "ed");
                }
            }
        }
    }

    private Date getTime(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.getTime();
    }
}
