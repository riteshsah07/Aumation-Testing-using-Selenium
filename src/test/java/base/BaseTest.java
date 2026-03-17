package base;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.*;

import io.github.bonigarcia.wdm.WebDriverManager;

import com.aventstack.extentreports.*;
import utils.ExtentManager;
import utils.ScreenshotUtil;

public class BaseTest {

    public WebDriver driver;
    public ExtentReports extent;
    public ExtentTest test;

    @BeforeSuite
    public void setupReport() {
        extent = ExtentManager.getReportInstance();
    }

    @BeforeMethod
    public void setup(Method method) {

        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();

        // Disable notifications
        options.addArguments("--disable-notifications");

        // Incognito mode (popup fix 🔥)
        options.addArguments("--incognito");

        // Disable password popup
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        prefs.put("profile.password_manager_leak_detection", false);

        options.setExperimentalOption("prefs", prefs);

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();

        // Start test in report
        test = extent.createTest(method.getName());
    }

    @AfterMethod
    public void tearDown(ITestResult result) {

        if (result.getStatus() == ITestResult.FAILURE) {
            String path = ScreenshotUtil.captureScreenshot(driver, result.getName());
            test.fail("Test Failed").addScreenCaptureFromPath(path);

        } else if (result.getStatus() == ITestResult.SUCCESS) {
            test.pass("Test Passed");
        }

        driver.quit();
    }

    @AfterSuite
    public void flushReport() {
        extent.flush();
    }
}