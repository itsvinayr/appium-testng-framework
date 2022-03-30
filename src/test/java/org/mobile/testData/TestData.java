package org.mobile.testData;

import org.testng.annotations.DataProvider;

public class TestData {

    @DataProvider(name="getData")
    public Object[][] getData(){
        return new Object[][]
        {
            {"one"},{"two"}
        };
    }

}
