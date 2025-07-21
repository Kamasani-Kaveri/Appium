import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Set;

public class AppBrowserSwitching {


    public static void main(String[] args) throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("appium:deviceName", "532bb9fa");
        capabilities.setCapability("appium:automationName", "UiAutomator2");
        capabilities.setCapability("appium:app", "/Users/kamasanikaveri/Downloads/APK FILES/airace.apk");
        capabilities.setCapability("appium:autoGrantPermissions", true);
        AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), capabilities);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement authBtn = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//android.widget.TextView[@text='Continue with Auth0']"))
        );
        authBtn.click();
        // Wait for WEBVIEW to be available
        try {
            Thread.sleep(5000); // static wait can be replaced with dynamic if needed
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Set<String> contexts = driver.getContextHandles();
        for (String context : contexts) {
            System.out.println("Context available: " + context);
            if (context.contains("WEBVIEW")) {
                driver.context(context);
                System.out.println("Switched to context: " + context);
                break;
            }
        }
        // Now in WEBVIEW - use web locators
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement email = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//android.webkit.WebView[@text=\"Sign In with Auth0\"]/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.view.View/android.view.View/android.view.View[3]/android.view.View/android.widget.EditText")));
        email.sendKeys("yuvrajt19052001@gmail.com");
        WebElement password = driver.findElement(By.xpath("//android.webkit.WebView[@text=\"Sign In with Auth0\"]/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.view.View/android.view.View/android.view.View[4]/android.view.View/android.view.View/android.widget.EditText"));
        password.sendKeys("Yuvraj@123");
        WebElement loginBtn = driver.findElement(By.xpath("//android.widget.Button[@text=\"Log In\"]"));
        loginBtn.click();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        driver.context("NATIVE_APP");
        System.out.println("Switched back to Native App");

    }

}







