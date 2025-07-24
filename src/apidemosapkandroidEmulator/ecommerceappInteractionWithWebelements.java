package apidemosapkandroidEmulator;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class ecommerceappInteractionWithWebelements {

    public static void main(String[] args) throws Exception {

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "Android");
        caps.setCapability("deviceName", "emulator-5554");
        caps.setCapability("automationName", "UiAutomator2");
        caps.setCapability("app", "/Users/kamasanikaveri/Downloads/General-Store.apk");

        // Correct chromedriver path
        caps.setCapability("chromedriverExecutable", "/Users/kamasanikaveri/Downloads/chromedriver-mac-x64/chromedriver");

        // Optional for permissions and WebView control
        caps.setCapability("autoGrantPermissions", true);
        caps.setCapability("autoWebview", false); // we'll switch manually

        AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), caps);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Step 1: Enter name
        WebElement nameField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//android.widget.EditText[@text='Enter name here']")));
        nameField.sendKeys("kaveri");

        // Step 2: Select Female
        driver.findElement(By.id("com.androidsample.generalstore:id/radioFemale")).click();

        // Step 3: Select country
        driver.findElement(By.id("com.androidsample.generalstore:id/spinnerCountry")).click();
        while (driver.findElements(By.xpath("//*[@text='Benin']")).isEmpty()) {
            scrollDown(driver);
        }
        driver.findElement(By.xpath("//*[@text='Benin']")).click();

        // Step 4: Click "Let's Shop"
        driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();

        // Step 5: Add first 2 items to cart
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='ADD TO CART']")));
        List<WebElement> addToCartButtons = driver.findElements(By.xpath("//*[@text='ADD TO CART']"));
        if (addToCartButtons.size() >= 2) {
            addToCartButtons.get(0).click();
            addToCartButtons.get(1).click();
        }

        // Step 6: Go to Cart
        driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
        Thread.sleep(4000);

        // Step 7: Tap checkbox
        WebElement checkbox = driver.findElement(By.className("android.widget.CheckBox"));
        tapElement(driver, checkbox);

        // Step 8: Long press on Terms and Conditions
        WebElement terms = driver.findElement(By.xpath("//*[@text='Please read our terms of conditions']"));
        longPress(driver, terms);

        // Step 9: Close popup
        driver.findElement(By.id("android:id/button1")).click();

        // Step 10: Click Proceed
        driver.findElement(By.id("com.androidsample.generalstore:id/btnProceed")).click();

        // Wait for WebView to load
        Thread.sleep(7000);

        // Step 11: Print all available contexts
        Set<String> contexts = driver.getContextHandles();
        for (String context : contexts) {
            System.out.println("Available Context: " + context);
        }

        // Step 12: Switch to WebView context
        for (String context : contexts) {
            if (context.toLowerCase().contains("webview")) {
                driver.context(context);
                System.out.println("Switched to WebView: " + context);
                break;
            }
        }

        Thread.sleep(3000);

        // Step 13: Perform search on Google


        driver.findElement(By.name("q")).sendKeys("Appium Automation", Keys.ENTER);

        Thread.sleep(3000);

        // Switch to native context
        driver.context("NATIVE_APP");

        // Press back
        driver.pressKey(new KeyEvent(AndroidKey.BACK));

        Thread.sleep(3000);

        //driver.quit();
    }

    // Scroll utility
    public static void scrollDown(AndroidDriver driver) {
        int height = driver.manage().window().getSize().getHeight();
        int width = driver.manage().window().getSize().getWidth();
        int startX = width / 2;
        int startY = (int) (height * 0.7);
        int endY = (int) (height * 0.3);

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 1);
        swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), startX, endY));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Arrays.asList(swipe));
    }

    // Tap utility
    public static void tapElement(AndroidDriver driver, WebElement element) {
        int centerX = element.getLocation().getX() + element.getSize().getWidth() / 2;
        int centerY = element.getLocation().getY() + element.getSize().getHeight() / 2;

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence tap = new Sequence(finger, 1);
        tap.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), centerX, centerY));
        tap.addAction(new Pause(finger, Duration.ofMillis(100)));
        tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        tap.addAction(new Pause(finger, Duration.ofMillis(100)));
        tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Arrays.asList(tap));
    }

    // Long press utility
    public static void longPress(AndroidDriver driver, WebElement element) {
        int centerX = element.getLocation().getX() + element.getSize().getWidth() / 2;
        int centerY = element.getLocation().getY() + element.getSize().getHeight() / 2;

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger2");
        Sequence longPress = new Sequence(finger, 1);
        longPress.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), centerX, centerY));
        longPress.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        longPress.addAction(new Pause(finger, Duration.ofSeconds(2)));
        longPress.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Arrays.asList(longPress));
    }
}
