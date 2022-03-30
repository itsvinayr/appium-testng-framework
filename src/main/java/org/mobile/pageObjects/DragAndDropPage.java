package org.mobile.pageObjects;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class DragAndDropPage {
    public DragAndDropPage(AndroidDriver<AndroidElement> driver){
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(className = "android.view.View")
    public List<AndroidElement> circles;

    public List<AndroidElement> getCircles() {
        return circles;
    }
}
