package org.mobile.base;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.offset.ElementOption;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Base {
    AppiumDriverLocalService appiumDriverLocalService;
    static AndroidDriver<AndroidElement> driver;
    static Properties properties;
    public Base(){
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(System.getProperty("user.dir")+
                    "//src//main//resources//global.properties");
            properties = new Properties();
            properties.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static AndroidDriver<AndroidElement> capabilities() throws IOException, InterruptedException {

        if(!properties.getProperty("device").equalsIgnoreCase("cloud")) {

            String device = "";
            String deviceName = "";

            File appDir = new File("src");
            File app = new File(appDir, properties.getProperty("appName"));

            if (System.getProperty("device") != null) {
                device = System.getProperty("device");
            } else {
                device = properties.getProperty("device");
            }

            if (System.getProperty("deviceName") != null) {
                deviceName = System.getProperty("deviceName");
            } else {
                deviceName = properties.getProperty("deviceName");
            }
            DesiredCapabilities capabilities = new DesiredCapabilities();
            if (device.equalsIgnoreCase("emulator")) {
                // configuration to run tests in emulator
                capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
                capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
                startEmulator();
            } else if (device.equalsIgnoreCase("real")) {
                // configuration to run tests on physical device
                capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
                capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
            } else if (device.equalsIgnoreCase("browser")) {
                // configuration to run tests on browser running in an emulator
                capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
                capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");
            }
            capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
            driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            return driver;
        }else{
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability("browserstack.user", "xxxx");
            caps.setCapability("browserstack.key", "xxxx");
            if(properties.getProperty("appName").equalsIgnoreCase("ApiDemos-debug.apk")) {
                caps.setCapability("app", "bs://xxxx");
            }
            caps.setCapability("device", "Google Pixel 3");
            caps.setCapability("os_version", "9.0");
            AndroidDriver<AndroidElement> driver = new AndroidDriver<AndroidElement>(
                    new URL("http://hub.browserstack.com/wd/hub"), caps);
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            return driver;
        }

    }
    public void startAppiumServer(){
        if(!properties.getProperty("device").equalsIgnoreCase("cloud")) {
            appiumDriverLocalService = AppiumDriverLocalService.buildDefaultService();
            appiumDriverLocalService.start();
        }
    }
    public void stopAppiumServer(){
        if(!properties.getProperty("device").equalsIgnoreCase("cloud")) {
            appiumDriverLocalService.stop();
        }
    }
    public void terminateAllNodes() throws IOException {
        if(!properties.getProperty("device").equalsIgnoreCase("cloud")) {
            Runtime.getRuntime().exec("taskkill /F /IM node.exe");
        }
    }
    public static boolean isAppiumServerRunningOn(int port){
        boolean isServerRunning = false;
        ServerSocket serverSocket;
        try{
            serverSocket = new ServerSocket(port);
            serverSocket.close();
        } catch (IOException e) {
            isServerRunning = true;
        }finally {
            serverSocket = null;
        }
        return isServerRunning;
    }
    public static void startEmulator() throws InterruptedException, IOException {
        if(!properties.getProperty("device").equalsIgnoreCase("cloud")) {
            Runtime.getRuntime().exec(System.getProperty("user.dir") + "\\src\\main\\resources\\openSimulator.bat");
            Thread.sleep(60000L);
        }
    }
    public static void getScreenshot(String name) throws IOException {
        if(!properties.getProperty("device").equalsIgnoreCase("cloud")) {
            File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(file, new File(System.getProperty("user.dir") +
                    "\\Reports\\Screenshots\\Fail\\" + name + ".png"));
        }
    }
}
