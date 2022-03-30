package org.mobile.base;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Base {
    AppiumDriverLocalService appiumDriverLocalService;
    public static AndroidDriver<AndroidElement> capabilities() throws IOException, InterruptedException {

        String device=""; String deviceName="";

        FileInputStream fileInputStream = new FileInputStream(System.getProperty("user.dir")+
                "//src//main//resources//global.properties");
        Properties properties = new Properties();
        properties.load(fileInputStream);

        File appDir = new File("src");
        File app = new File(appDir, properties.getProperty("appName"));

        if(System.getProperty("device")!=null){
            device = System.getProperty("device");
        }else {
            device = properties.getProperty("device");
        }
        if(System.getProperty("deviceName")!=null){
            deviceName = System.getProperty("deviceName");
        }else {
            device = properties.getProperty("deviceName");
        }

        DesiredCapabilities capabilities = new DesiredCapabilities();
        if(device.equalsIgnoreCase("emulator")) {
            // configuration to run tests in emulator
            capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
            capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
            startEmulator();
        }else if(device.equalsIgnoreCase("real")){
            // configuration to run tests on physical device
            capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
            capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
        }else if(device.equalsIgnoreCase("browser")){
            // configuration to run tests on browser running in an emulator
            capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
            capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");
        }
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
        AndroidDriver<AndroidElement> driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        return driver;

    }
    public void startAppiumServer(){
        appiumDriverLocalService = AppiumDriverLocalService.buildDefaultService();
        appiumDriverLocalService.start();
    }
    public void stopAppiumServer(){
        appiumDriverLocalService.stop();
    }
    public void terminateAllNodes() throws IOException {
        Runtime.getRuntime().exec("taskkill /F /IM node.exe");
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
        Runtime.getRuntime().exec(System.getProperty("user.dir")+"\\src\\main\\resources\\openSimulator.bat");
        Thread.sleep(60000L);
    }
}
