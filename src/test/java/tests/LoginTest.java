package tests;

import base.BaseTest;
import io.qameta.allure.*;
import org.example.pages.LoginPage;
import org.testng.annotations.Test;

@Epic("Authentication")
@Feature("Login Flow")
public class LoginTest extends BaseTest {

    @Test(dependsOnMethods = "testSuccessfulSignup", groups = { "smoke", "regression" })
    @Story("Login with valid credentials")
    @Description("Verify that the user can login successfully using credentials created during signup.")
    @Severity(SeverityLevel.BLOCKER)
    public void testLoginAfterSignup() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickLoginButton(driver);
        loginPage.enterEmail(SignupTest.signupEmail);
        loginPage.enterPassword(SignupTest.signupPassword);
        loginPage.clickLogin();
    }

    @Test(groups = { "regression" })
    public void testInvalidLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickLoginButton(driver);
        loginPage.enterEmail("invalid@example.com");
        loginPage.enterPassword("wrongPassword");
        loginPage.clickLogin();
        if (loginPage.isErrorMessageDisplayed()) {
            System.out.println("Login Failed or invalid login. Please try again!");
        }
    }
}
