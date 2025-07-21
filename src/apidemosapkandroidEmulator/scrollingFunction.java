package apidemosapkandroidEmulator;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Collections;

public class scrollingFunction {

    public static void main(String[] args) throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("appium:deviceName", "Android Emulator");
        capabilities.setCapability("appium:automationName", "UiAutomator2");
        capabilities.setCapability("appium:app", "/Users/kamasanikaveri/Apps/ApiDemos-debug.apk");
        capabilities.setCapability("appium:autoGrantPermissions", true);

        AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), capabilities);

        // Scrolling Events
        driver.findElement(By.xpath("//android.widget.TextView[@content-desc='Views']")).click();

        // Step 2: Scroll/swipe up until WebView is found
        boolean found = false;
        int maxSwipes = 6;
        int swipeCount = 0;

        while (!found && swipeCount < maxSwipes) {
            try {
                WebElement webViewElement = driver.findElement(By.xpath("//android.widget.TextView[@content-desc='WebView']"));
                webViewElement.click();
                found = true;
                System.out.println("WebView element found and clicked.");
            } catch (NoSuchElementException e) {
                swipeUp(driver);
                swipeCount++;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }

        if (!found) {
            System.out.println("WebView element not found after swiping.");
        }

        driver.quit();
    }

    // Swipe Up
    public static void swipeUp(AndroidDriver driver) {
        Dimension size = driver.manage().window().getSize();
        int startX = size.width / 2;
        int startY = (int) (size.height * 0.8);
        int endY = (int) (size.height * 0.2);
        performSwipe(driver, startX, startY, startX, endY);
        System.out.println("Swiped Up");
    }

    // Swipe Logic
    public static void performSwipe(AndroidDriver driver, int startX, int startY, int endX, int endY) {
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 1);

        swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(600), PointerInput.Origin.viewport(), endX, endY));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Collections.singletonList(swipe));
    }
}






