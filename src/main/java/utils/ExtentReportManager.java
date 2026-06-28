package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class ExtentReportManager {
    private static final String REPORT_FOLDER = "target/extent-report";
    private static final String SCREENSHOT_FOLDER = REPORT_FOLDER + "/screenshots";
    private static final String REPORT_FILE = REPORT_FOLDER + "/extent-report.html";

    private static ExtentReports extent;
    private static final ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    private static boolean initialized = false;

    public static synchronized void initReport() {
        if (initialized) {
            return;
        }
        initialized = true;
        try {
            Files.createDirectories(Path.of(SCREENSHOT_FOLDER));
            ExtentSparkReporter spark = new ExtentSparkReporter(REPORT_FILE);
            extent = new ExtentReports();
            extent.attachReporter(spark);
        } catch (IOException e) {
            throw new RuntimeException("Unable to initialize report folder: " + e.getMessage(), e);
        }
    }

    public static void setCurrentTestContext(String testClass, String testMethod) {
        if (extent == null) {
            initReport();
        }
        ExtentTest extentTest = extent.createTest(testClass + " - " + testMethod);
        test.set(extentTest);
    }

    public static void clearCurrentTestContext() {
        test.remove();
    }

    private static Status mapStatus(ReportStatus status) {
        switch (status) {
            case PASS:
                return Status.PASS;
            case FAIL:
                return Status.FAIL;
            case INFO:
            default:
                return Status.INFO;
        }
    }

    public static synchronized void addReportEntry(WebDriver driver, String testClass, String testMethod, String description, ReportStatus status, boolean screenshot) {
        if (test.get() == null) {
            setCurrentTestContext(testClass, testMethod);
        }
        ExtentTest currentTest = test.get();
        Status extentStatus = mapStatus(status);
        System.out.println(String.format("[LOG] [%s] [%s.%s] - %s", status, testClass, testMethod, description));
        if (screenshot && driver != null) {
            String screenshotPath = captureScreenshot(driver, testClass, testMethod);
            if (screenshotPath != null) {
                String relativePath = "screenshots/" + new File(screenshotPath).getName();
                currentTest.log(extentStatus, description,
                    com.aventstack.extentreports.MediaEntityBuilder.createScreenCaptureFromPath(relativePath).build());
                return;
            }
        }
        currentTest.log(extentStatus, description);
    }

    public static synchronized void addReportEntry(String testClass, String testMethod, String description, ReportStatus status, boolean screenshot) {
        if (test.get() == null) {
            setCurrentTestContext(testClass, testMethod);
        }
        ExtentTest currentTest = test.get();
        Status extentStatus = mapStatus(status);
        System.out.println(String.format("[LOG] [%s] [%s.%s] - %s", status, testClass, testMethod, description));
        currentTest.log(extentStatus, description);
    }

    public static synchronized void addReportEntry(String description, ReportStatus status, boolean screenshot) {
        ExtentTest currentTest = test.get();
        if (currentTest == null) {
            setCurrentTestContext("UnknownClass", "UnknownMethod");
            currentTest = test.get();
        }
        Status extentStatus = mapStatus(status);
        System.out.println(String.format("[LOG] [%s] - %s", status, description));
        currentTest.log(extentStatus, description);
    }

    public static synchronized void addReportEntry(String description, ReportStatus status, boolean screenshot, WebDriver driver) {
        ExtentTest currentTest = test.get();
        if (currentTest == null) {
            setCurrentTestContext("UnknownClass", "UnknownMethod");
            currentTest = test.get();
        }
        Status extentStatus = mapStatus(status);
        System.out.println(String.format("[LOG] [%s] - %s", status, description));
        if (screenshot && driver != null) {
            String screenshotPath = captureScreenshot(driver, "Step", "Screenshot");
            if (screenshotPath != null) {
                String relativePath = "screenshots/" + new File(screenshotPath).getName();
                currentTest.log(extentStatus, description,
                    com.aventstack.extentreports.MediaEntityBuilder.createScreenCaptureFromPath(relativePath).build());
                return;
            }
        }
        currentTest.log(extentStatus, description);
    }

    public static synchronized void generateReport() {
        if (extent != null) {
            extent.flush();
        }
    }

    private static String captureScreenshot(WebDriver driver, String testClass, String testMethod) {
        try {
            if (!(driver instanceof TakesScreenshot)) {
                return null;
            }

            Path screenshotFile = Path.of(SCREENSHOT_FOLDER,
                    testClass + "_" + testMethod + "_" + System.currentTimeMillis() + ".png");
            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            Files.copy(srcFile.toPath(), screenshotFile, StandardCopyOption.REPLACE_EXISTING);
            return screenshotFile.toString().replace("\\", "/");
        } catch (Exception e) {
            System.out.println("Unable to capture screenshot: " + e.getMessage());
            return null;
        }
    }
}
