package apidemosapkandroidEmulator;


import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class ecommerceappSwitchToWebview {
    public static void main(String[] args) throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("appium:deviceName", "Android Emulator");
        capabilities.setCapability("appium:automationName", "UiAutomator2");
        capabilities.setCapability("appium:app", "/Users/kamasanikaveri/Downloads/General-Store.apk");
        capabilities.setCapability("appium:autoGrantPermissions", true);




        AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), capabilities);

        // Step 1: Enter name
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement ele1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.EditText[@text='Enter name here']")));
        ele1.sendKeys("kaveri");

        // Step 2: Select Female radio button
        WebElement femaleRadio = driver.findElement(By.id("com.androidsample.generalstore:id/radioFemale"));
        femaleRadio.click();

        // Step 3: Click country dropdown
        driver.findElement(By.id("com.androidsample.generalstore:id/spinnerCountry")).click();

        // Step 4: Manual scroll until "Benin" is visible
        while (driver.findElements(By.xpath("//*[@text='Benin']")).isEmpty()) {
            scrollDown(driver);
        }
        driver.findElement(By.xpath("//*[@text='Benin']")).click();

        // Step 5: Click "Let's Shop"
        driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();

        //Step 6: Add first 2 products to cart using get(0), get(1)
        // Wait for at least one "ADD TO CART" button
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='ADD TO CART']")));

        List<WebElement> addToCartButtons = driver.findElements(By.xpath("//*[@text='ADD TO CART']"));
        System.out.println("Number of 'ADD TO CART' buttons: " + addToCartButtons.size());

        if (addToCartButtons.size() >= 2) {
            addToCartButtons.get(0).click();
            addToCartButtons.get(1).click();
        } else {
            System.out.println(" Not enough products to add to cart.");
        }

        //Step 7: Click Cart
        driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();

        //Step8: validating total amount generating functionality
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        //Mobile Gestures
        // 1. Tap on the CheckBox
        WebElement checkbox = driver.findElement(By.className("android.widget.CheckBox"));

// Calculate center of checkbox
        int cbX = checkbox.getLocation().getX() + checkbox.getSize().getWidth() / 2;
        int cbY = checkbox.getLocation().getY() + checkbox.getSize().getHeight() / 2;

// Tap using W3C PointerInput
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
        Sequence tap = new Sequence(finger, 1);
        tap.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), cbX, cbY));
        tap.addAction(new Pause(finger, Duration.ofMillis(100)));
        tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        tap.addAction(new Pause(finger, Duration.ofMillis(100)));  // small press duration
        tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(Arrays.asList(tap));


        // 2. Long press on "Terms and Conditions"
        WebElement tc = driver.findElement(By.xpath("//*[@text='Please read our terms of conditions']"));

        PointerInput finger2 = new PointerInput(PointerInput.Kind.TOUCH, "finger2");
        Sequence longPress = new Sequence(finger2, 1);

        int tcX = tc.getLocation().getX() + tc.getSize().getWidth() / 2;
        int tcY = tc.getLocation().getY() + tc.getSize().getHeight() / 2;

        longPress.addAction(finger2.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), tcX, tcY));
        longPress.addAction(finger2.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        longPress.addAction(new Pause(finger, Duration.ofSeconds(2))); // Long press for 2 seconds
        longPress.addAction(finger2.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Arrays.asList(longPress));

        // 3. Click Close button
        driver.findElement(By.id("android:id/button1")).click();

        // 4. Click Proceed
        driver.findElement(By.id("com.androidsample.generalstore:id/btnProceed")).click();

        //Switch to webview
        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        //  all available context handles
        Set<String> contexts = driver.getContextHandles();

        for (String context : contexts) {
            System.out.println("Context: " + context);
        }

       //  current context
        System.out.println("Current Context: " + driver.getContext());



    }


    // Scroll down using PointerInput sequence
    public static void scrollDown(AndroidDriver driver) {
        int screenHeight = driver.manage().window().getSize().getHeight();
        int screenWidth = driver.manage().window().getSize().getWidth();

        int startX = screenWidth / 2;
        int startY = (int) (screenHeight * 0.7);
        int endY = (int) (screenHeight * 0.3);

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 1);
        swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(600), PointerInput.Origin.viewport(), startX, endY));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(Arrays.asList(swipe));
    }
}
