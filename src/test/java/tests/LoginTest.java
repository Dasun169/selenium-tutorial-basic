package tests;

import base.BaseTest;
import io.qameta.allure.*;
import org.example.pages.HomePage;
import org.example.pages.LoginPage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import utils.ExtentReportManager;
import utils.ReportStatus;
import utils.TestDataProviders;

@Epic("Authentication")
@Feature("Login Flow")
public class LoginTest extends BaseTest {

    @Test(dataProvider = "ValidLogin", dataProviderClass = TestDataProviders.class, groups = { "smoke", "regression" })
    @Story("Login with valid credentials")
    @Description("Verify that the user can login successfully using credentials created during signup.")
    public void testSuccessLogin(String username, String password) {
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);

        homePage.clickLoginButton();
        loginPage.loginPageValidation();
        loginPage.enterEmail(username);
        loginPage.enterPassword(password);
        loginPage.clickLogin();
        homePage.homePageValidation();

        ExtentReportManager.addReportEntry(driver,
                getClass().getSimpleName(),
                "testSuccessLogin",
                "Valid login executed using Excel sheet data.",
                ReportStatus.PASS,
                true);
    }

    @Test(dataProvider = "InvalidLogin", dataProviderClass = TestDataProviders.class, groups = { "regression" })
    @Story("Invalid login")
    @Description("Verify that the user cannot login with invalid credentials.")
    public void testInvalidLogin(String username, String password) {
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);

        homePage.clickLoginButton();
        loginPage.loginPageValidation();
        loginPage.enterEmail(username);
        loginPage.enterPassword(password);
        loginPage.clickLogin();
        if (loginPage.isErrorMessageDisplayed()) {
            loginPage.invalidPasswordValidation();
        }

        ExtentReportManager.addReportEntry(driver,
                getClass().getSimpleName(),
                "testInvalidLogin",
                "Invalid login validation executed using Excel sheet data.",
                ReportStatus.PASS,
                true);
    }

    @Test(dataProvider = "InvalidUsername", dataProviderClass = TestDataProviders.class, groups = { "smoke", "regression" })
    @Story("Login with valid credentials")
    @Description("Verify that the user can login successfully using credentials created during signup.")
    public void testInvalidUsername(String username, String password) {
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);

        homePage.clickLoginButton();
        loginPage.loginPageValidation();
        loginPage.enterEmail(username);
        loginPage.enterPassword(password);
        loginPage.clickLogin();
        loginPage.invalidUserNameValidation();

        ExtentReportManager.addReportEntry(driver,
                getClass().getSimpleName(),
                "testInvalidUsername",
                "Invalid username validation executed using Excel sheet data.",
                ReportStatus.PASS,
                true);
    }

    @Test(dataProvider = "EmptyCredentials", dataProviderClass = TestDataProviders.class, groups = { "smoke", "regression" })
    @Story("Login with valid credentials")
    @Description("Verify that the user can login successfully using credentials created during signup.")
    public void testEmptyCredentials(String username, String password) {
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);

        homePage.clickLoginButton();
        loginPage.loginPageValidation();
        loginPage.enterEmail(username);
        loginPage.enterPassword(password);
        loginPage.clickLogin();
        loginPage.EmptyUserNamePasswordValidation();

        ExtentReportManager.addReportEntry(driver,
                getClass().getSimpleName(),
                "testEmptyCredentials",
                "Empty credentials validation executed using Excel sheet data.",
                ReportStatus.PASS,
                true);
    }

    @AfterMethod(alwaysRun = true)
    public void navigateBackToHome() {
        try {
            HomePage homePage = new HomePage(driver);
            homePage.navigateToHome();
        } catch (Exception e) {
            System.out.println("Unable to navigate to Home Page: " + e.getMessage());
        }
    }
}
