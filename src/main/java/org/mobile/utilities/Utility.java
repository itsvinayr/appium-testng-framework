package org.mobile.utilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class Utility {
    public AndroidDriver<AndroidElement> driver;
    public Utility(AndroidDriver<AndroidElement> driver){
        this.driver = driver;
    }

    public AndroidElement scrollUpto(String text){
        return driver.findElementByAndroidUIAutomator(
                "new UiScrollable(new UiSelector()).scrollIntoView(text(\""+text+"\"));");
    }
}
