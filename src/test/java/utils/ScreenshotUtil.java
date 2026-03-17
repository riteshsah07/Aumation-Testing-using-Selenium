package utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

public class ScreenshotUtil {

    public static String captureScreenshot(WebDriver driver, String testName) {

        try {
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            String path = "screenshots/" + testName + ".png";

            Files.createDirectories(Path.of("screenshots")); // ensure folder exists
            Files.copy(src.toPath(), Path.of(path));

            return path;

        } catch (Exception e) {
            return null;
        }
    }
}