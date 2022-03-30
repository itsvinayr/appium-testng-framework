package org.mobile.listeners;

import org.mobile.utilities.Utility;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

public class Listeners implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        String name = result.getName();
        try {
            Utility.getScreenshot(name);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
