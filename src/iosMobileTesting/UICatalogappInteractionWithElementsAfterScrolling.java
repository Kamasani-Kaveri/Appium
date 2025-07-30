package iosMobileTesting;

import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class UICatalogappInteractionWithElementsAfterScrolling {
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
        driver.navigate().back();


        // Perform scroll gesture from bottom to top (i.e., scroll up)
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 1);

        swipe.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), 200, 600));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(700), PointerInput.Origin.viewport(), 200, 100));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Arrays.asList(swipe));


        driver.findElement(By.xpath("//*[@name=\"Steppers\"]")).click();

        driver.findElement(By.xpath("(//XCUIElementTypeButton[@name=\"Increment\"])")).click();
        driver.findElement(By.xpath("(//XCUIElementTypeButton[@name=\"Increment\"])")).click();


        List<WebElement> labels = driver.findElements(By.className("XCUIElementTypeStaticText"));
        System.out.println(labels.get(1).getText());
        System.out.println(labels.get(2).getText());




        driver.findElement(By.xpath("(//XCUIElementTypeButton[@name=\"Decrement\"])")).click();

        System.out.println(labels.get(1).getText());













    }
}



