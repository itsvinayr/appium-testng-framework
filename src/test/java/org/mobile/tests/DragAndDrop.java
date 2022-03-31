package org.mobile.tests;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.offset.ElementOption;
import org.mobile.base.Base;
import org.mobile.pageObjects.DragAndDropPage;
import org.mobile.pageObjects.HomePage;
import org.mobile.pageObjects.ViewsPage;
import org.mobile.testData.TestData;
import org.mobile.utilities.Utility;
import org.testng.annotations.*;

import java.io.IOException;

public class DragAndDrop extends Base {

    @BeforeTest
    public void closeAllNodes() throws IOException {
        terminateAllNodes();
    }

    @BeforeMethod
    public void invokeServer() {
        if(!isAppiumServerRunningOn(4723)) {
            startAppiumServer();
        }else{
            System.out.println("****Server is already running*****");
        }
    }

    @Test(dataProvider = "getData", dataProviderClass = TestData.class)
    public void testDragAndDrop(String iteration) throws IOException, InterruptedException {

        /*
         * Click on Views --> Drag and Drop --> Select the Dot, drag and drop
         */

        AndroidDriver<AndroidElement> androidDriver = capabilities();
        HomePage homePage = new HomePage(androidDriver);
        ViewsPage viewsPage = new ViewsPage(androidDriver);
        DragAndDropPage dragAndDropPage = new DragAndDropPage(androidDriver);
        Utility utility = new Utility(androidDriver);

        utility.scrollUpto("Views");
        homePage.getViewsMenuItem().click();
        viewsPage.getDragAndDropMenuItem().click();
        AndroidElement src = dragAndDropPage.getCircles().get(0);
        AndroidElement dest1 = dragAndDropPage.getCircles().get(1);

        TouchAction<?> touchAction = new TouchAction<>(androidDriver);
        touchAction.longPress(new LongPressOptions().withElement(new ElementOption().withElement(src))).
                moveTo(new ElementOption().withElement(dest1)).release().perform();

        androidDriver.quit();
    }

    @AfterMethod
    public void terminateServer(){
        stopAppiumServer();
    }

    @AfterTest
    public void closingConnections() throws IOException {
        terminateAllNodes();
    }

}
