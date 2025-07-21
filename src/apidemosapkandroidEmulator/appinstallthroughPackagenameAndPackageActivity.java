package apidemosapkandroidEmulator;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class appinstallthroughPackagenameAndPackageActivity {

    public static void main(String[] args) {

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", "Android");
        caps.setCapability("deviceName", "Android Device"); //  device name
        caps.setCapability("automationName", "UiAutomator2");
        caps.setCapability("udid", "YOUR_DEVICE_ID");
        caps.setCapability("appPackage", "com.darden.mobile.olivegarden");
        caps.setCapability("appActivity", "com.darden.mobile.mapp.MainActivity");
        caps.setCapability("noReset", true); // Keep app state

        AndroidDriver driver = null;
        try {
            driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), caps);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
}
