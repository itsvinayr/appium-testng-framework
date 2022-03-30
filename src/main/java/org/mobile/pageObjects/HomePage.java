package org.mobile.pageObjects;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    public HomePage(AndroidDriver<AndroidElement> driver){
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Views']")
    public AndroidElement viewsMenuItem;

    public AndroidElement getViewsMenuItem() {
        return viewsMenuItem;
    }
}
