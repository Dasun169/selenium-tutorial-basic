package tests;
import base.BaseTest;
import org.example.pages.LoginPage;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test(dependsOnMethods = "tests.SignupTest.testSuccessfulSignup")
    public void testLoginAfterSignup() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickLoginButton(driver);
        loginPage.enterEmail(SignupTest.signupEmail);
        loginPage.enterPassword(SignupTest.signupPassword);
        loginPage.clickLogin();
    }

    @Test
    public void testInvalidLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickLoginButton(driver);
        loginPage.enterEmail("invalid@example.com");
        loginPage.enterPassword("wrongPassword");
        loginPage.clickLogin();
        if (loginPage.isErrorMessageDisplayed()){
            System.out.println("Login Failed or invalid login. Please try again!");
        }
    }
}
