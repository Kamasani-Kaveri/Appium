package iosMobileTesting;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

public class SafariBrowser {
    IOSDriver driver;

    @Test
    public void brows() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("appium:deviceName", "iPhone 16 Pro");
        capabilities.setCapability("appium:platformVersion", "18.5");
        capabilities.setCapability("browserName", "Safari");
        capabilities.setCapability("appium:automationName", "XCUITest");

        driver = new IOSDriver(new URL("http://127.0.0.1:4723"), capabilities);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        // 1. Open Gmail login page
        driver.get("https://accounts.google.com");

        // 2. Wait for email input and enter dummy email
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        WebElement emailInput = driver.findElement(By.xpath("//input[@type='email']"));
        emailInput.sendKeys("dummyemail@gmail.com");

        // 3. Click 'Next'
        WebElement nextButton = driver.findElement(By.xpath("//span[contains(text(),'Next')]"));
        nextButton.click();



        driver.quit();
    }
}