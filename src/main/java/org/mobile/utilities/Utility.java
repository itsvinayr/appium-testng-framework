package org.mobile.utilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;

public class Utility {
    public static AndroidDriver<AndroidElement> driver;
    public Utility(AndroidDriver<AndroidElement> driver){
        this.driver = driver;
    }

    public AndroidElement scrollUpto(String text){
        return driver.findElementByAndroidUIAutomator(
                "new UiScrollable(new UiSelector()).scrollIntoView(text(\""+text+"\"));");
    }

    public static void getScreenshot(String name) throws IOException {
        File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file, new File(System.getProperty("user.dir")+
                "\\Reports\\Screenshots\\Fail\\"+name+".png"));
    }
}
