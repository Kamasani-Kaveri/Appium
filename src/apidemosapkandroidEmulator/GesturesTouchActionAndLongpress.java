package apidemosapkandroidEmulator;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Collections;

public class GesturesTouchActionAndLongpress {
    public static void main(String[] args) throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("appium:deviceName", "Android Emulator");
        capabilities.setCapability("appium:automationName", "UiAutomator2");
        capabilities.setCapability("appium:app", "/Users/kamasanikaveri/Apps/ApiDemos-debug.apk");
        capabilities.setCapability("appium:autoGrantPermissions", true);

        AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), capabilities);

        // Tap on "Views"
        driver.findElement(By.xpath("//android.widget.TextView[@content-desc='Views']")).click();

        // Tap on "Expandable Lists"
        WebElement expandableList = driver.findElement(By.xpath("//android.widget.TextView[@content-desc='Expandable Lists']"));

        int centerX1 = expandableList.getRect().getX() + (expandableList.getRect().getWidth() / 2);
        int centerY1 = expandableList.getRect().getY() + (expandableList.getRect().getHeight() / 2);

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence tap = new Sequence(finger, 1);
        tap.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), centerX1, centerY1));
        tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(Collections.singletonList(tap));

        // Click on "1. Custom Adapter"
        driver.findElement(By.xpath("//android.widget.TextView[@content-desc='1. Custom Adapter']")).click();

        // Long press on "People Names"
        WebElement peopleNames = driver.findElement(By.xpath("//android.widget.TextView[@text='People Names']"));

        int centerX2 = peopleNames.getRect().getX() + (peopleNames.getRect().getWidth() / 2);
        int centerY2 = peopleNames.getRect().getY() + (peopleNames.getRect().getHeight() / 2);

        Sequence longPress = new Sequence(finger, 2);
        longPress.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), centerX2, centerY2));
        longPress.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        longPress.addAction(new Pause(finger, Duration.ofSeconds(2))); // Long press for 2 seconds
        longPress.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(Collections.singletonList(longPress));

        // Optional: Add sleep to view the popup or behavior
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.quit();
    }
}