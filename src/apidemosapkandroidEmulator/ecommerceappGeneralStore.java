package apidemosapkandroidEmulator;

import org.testng.Assert;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Collections;
import java.util.List;

public class ecommerceappGeneralStore {

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
        //driver.findElement(By.id("com.androidsample.generalstore:id/radioFemale")).click();
        WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement femaleRadio = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.id("com.androidsample.generalstore:id/radioFemale")));
        femaleRadio.click();


        // Step 3: Click country dropdown
        driver.findElement(By.id("com.androidsample.generalstore:id/spinnerCountry")).click();

        // Step 4: Scroll until "Benin" is visible and click
        boolean found = false;
        int maxSwipes = 8;

        for (int i = 0; i < maxSwipes; i++) {
            try {
                WebElement benin = driver.findElement(By.xpath("//android.widget.TextView[@resource-id='android:id/text1' and @text='Benin']"));
                benin.click();
                System.out.println(" 'Benin' selected.");
                found = true;
                break;
            } catch (NoSuchElementException e) {
                System.out.println(" 'Benin' not visible. Swiping... Attempt: " + (i + 1));
                swipeUp(driver);
                waitFor(1000);
            }
        }

        if (!found) {
            System.out.println(" 'Benin' not found after max swipes.");
        }

        // Step 5: Click "Let's Shop" button
        driver.findElement(By.xpath("//android.widget.Button[@resource-id='com.androidsample.generalstore:id/btnLetsShop']")).click();
        System.out.println("Clicked on 'Let's Shop' button.");

        //step6: Toast messages
       /* String toastMessage = driver.findElement(By.xpath("//android.widget.Toast[1]")).getAttribute("name");
        System.out.println(toastMessage);*/

        // Step 7: Scroll to "Jordan 6 Rings" and click its Add to Cart button
        boolean productFound = false;
        int maxScrolls = 8;

        for (int scroll = 0; scroll < maxScrolls; scroll++) {
            java.util.List<WebElement> productNames = driver.findElements(By.id("com.androidsample.generalstore:id/productName"));

            for (int i = 0; i < productNames.size(); i++) {
                String name = productNames.get(i).getText();
                if (name.equals("Jordan 6 Rings")) {
                    // Found the product; click the corresponding Add to Cart button
                    driver.findElements(By.id("com.androidsample.generalstore:id/productAddCart")).get(i).click();
                    System.out.println("Clicked 'Add to Cart' for Jordan 6 Rings.");
                    productFound = true;
                    break;
                }
            }

            if (productFound) break;

            swipeUp(driver);
            waitFor(1000);
            System.out.println("Scrolling to find 'Jordan 6 Rings'... Attempt " + (scroll + 1));
        }

        if (!productFound) {
            System.out.println(" 'Jordan 6 Rings' not found after maximum scrolls.");
        }

        // Navigate to cart screen
        driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();

        // Wait until the cart screen product name is visible and then verify
        WebDriverWait cartWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement cartProduct = cartWait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("com.androidsample.generalstore:id/productName"))
        );
        String actualText = cartProduct.getText();
        Assert.assertEquals(actualText, "Jordan 6 Rings", "Product name mismatch!");



        //driver.quit();
    }

    // Swipe Up
    public static void swipeUp(AndroidDriver driver) {
        Dimension size = driver.manage().window().getSize();
        int startX = size.width / 2;
        int startY = (int) (size.height * 0.7);
        int endY = (int) (size.height * 0.3);
        performSwipe(driver, startX, startY, startX, endY);
    }

    // Perform swipe using W3C pointer input
    public static void performSwipe(AndroidDriver driver, int startX, int startY, int endX, int endY) {
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 1);

        swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), endX, endY));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Collections.singletonList(swipe));
    }

    // Simple wait utility
    public static void waitFor(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }


    }


}