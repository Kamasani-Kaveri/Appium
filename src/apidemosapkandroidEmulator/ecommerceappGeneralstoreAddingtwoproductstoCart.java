package apidemosapkandroidEmulator;

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
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ecommerceappGeneralstoreAddingtwoproductstoCart {
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