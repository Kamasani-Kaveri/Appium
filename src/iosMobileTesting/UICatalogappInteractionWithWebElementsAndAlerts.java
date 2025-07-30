package iosMobileTesting;

import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class UICatalogappInteractionWithWebElementsAndAlerts {
    public static void main(String[] args) throws MalformedURLException {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("appium:deviceName", "iPhone 16 Pro");
        capabilities.setCapability("appium:platformVersion", "18.5");
        capabilities.setCapability("appium:automationName", "XCUITest");
        capabilities.setCapability("appium:app", "/Users/kamasanikaveri/Library/Developer/Xcode/DerivedData/UICatalog-duzsiesdssrgpufptmevqrwavjxx/Build/Products/Debug-iphonesimulator/UICatalog.app");
        capabilities.setCapability("appium:autoGrantPermissions", true);

        IOSDriver driver = new IOSDriver(new URL("http://127.0.0.1:4723"), capabilities);

        driver.findElement(By.xpath("//*[@name=\"Alert Views\"]")).click();
        driver.findElement(By.xpath("//XCUIElementTypeStaticText[@name=\"Text Entry\"]")).click();
        driver.findElement(By.xpath("//XCUIElementTypeOther[@name=\"CenterPageView\"]/XCUIElementTypeOther[1]")).sendKeys("kaveri");
        driver.findElement(By.name("OK")).click();







    }
}

