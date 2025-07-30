package iosMobileTesting;

import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class UICatalogappDesiredCapabilitiesAndAppInstallationInSimulator {
    public static void main(String[] args) throws MalformedURLException {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("appium:deviceName", "iPhone 16 Pro");
        capabilities.setCapability("appium:platformVersion", "18.5");
        capabilities.setCapability("appium:automationName", "XCUITest");
        capabilities.setCapability("appium:app", "/Users/kamasanikaveri/Library/Developer/Xcode/DerivedData/UICatalog-duzsiesdssrgpufptmevqrwavjxx/Build/Products/Debug-iphonesimulator/UICatalog.app");
        capabilities.setCapability("appium:autoGrantPermissions", true);


        IOSDriver driver = new IOSDriver(new URL("http://127.0.0.1:4723"), capabilities);

       


    }
}
