import io.appium.java_client.ios.IOSDriver;


import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class IOSDEMO {
    public static void main(String[] args) throws MalformedURLException {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("appium:deviceName", "iPhone 16 Pro");
        capabilities.setCapability("appium:platformVersion", "18.5");
        capabilities.setCapability("appium:automationName", "XCUITest");
        capabilities.setCapability("appium:app", "/Users/kamasanikaveri/Downloads/AirACE.app.zip");
        capabilities.setCapability("appium:autoGrantPermissions", true);
        capabilities.setCapability("appium:udid", "0FEFA49D-A7B6-42EE-90E3-848824B0D273");

        IOSDriver driver = new IOSDriver(new URL("http://127.0.0.1:4723"), capabilities);

        try {
            driver.switchTo().alert().accept(); // for system alert
            System.out.println("Alert accepted");
        } catch (Exception e) {
            System.out.println("No alert present");
        }

        // Wait and click "Get Started" button
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement getStartedBtn = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("(//XCUIElementTypeOther[@name='Get Started'])[2]"))
        );
        getStartedBtn.click();

        // Enter phone number
        WebElement phoneField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//XCUIElementTypeTextField[@value='4XX XXX XXX']")));
        phoneField.sendKeys("4123 345 678");

        // Taking screenshot after phone number is entered
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            File destFile = new File("phone_number_entered.png");

            FileUtils.copyFile(screenshot, destFile);
            System.out.println("Screenshot saved at: " + destFile.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("Failed to save screenshot: " + e.getMessage());
        }










    }
}




       




