package utils;

import org.testng.annotations.DataProvider;

public class TestDataProviders {

    @DataProvider(name = "ValidLogin")
    public static Object[][] validLogin() {
        return ExcelUtils.getSheetData("ValidLogin");
    }

    @DataProvider(name = "InvalidLogin")
    public static Object[][] invalidLogin() {
        return ExcelUtils.getSheetData("InvalidLogin");
    }

    @DataProvider(name = "InvalidUsername")
    public static Object[][] invalidUsername() {
        return ExcelUtils.getSheetData("InvalidUsername");
    }

    @DataProvider(name = "EmptyCredentials")
    public static Object[][] emptyCredentials() {
        return ExcelUtils.getSheetData("EmptyCredentials");
    }
}
