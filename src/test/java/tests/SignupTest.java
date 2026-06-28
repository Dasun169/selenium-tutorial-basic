package tests;

import base.BaseTest;
import org.example.pages.SignupPage;
import org.example.pages.HomePage;
import org.example.pages.LoginPage;
import org.openqa.selenium.TimeoutException;
import org.testng.annotations.Test;
import java.util.Random;
import java.util.regex.Pattern;

public class SignupTest extends BaseTest {
    public static String signupEmail;
    public static String signupPassword;
    public static String phoneNumber;

    @Test(groups = { "signup", "smoke", "regression" })
    public void testSuccessfulSignup() {
        SignupPage signupPage = new SignupPage(driver);
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);

        homePage.clickLoginButton();
        loginPage.clickSignUpLink();

        int randomNum = new Random().nextInt(10000); // 0-9999
        signupEmail = "testuser" + randomNum + "@example.com";
        signupPassword = "TestPass@" + randomNum;
        phoneNumber = "0713116142";

        if (!isValidEmail(signupEmail)) {
            System.out.println("Warning: Invalid email format -> " + signupEmail);
        }

        if (!isValidPhoneNumber(phoneNumber)) {
            System.out.println("Warning: Invalid phone number format -> " + phoneNumber);
        }

        if (!isStrongPassword(signupPassword)) {
            System.out.println("Warning: Weak password -> " + signupPassword +
                    ". Password should have at least 8 characters, one uppercase letter, one digit, and one special character.");
        }

        signupPage.fillSignupForm("John", "Doe", signupEmail, phoneNumber, signupPassword);
        try {
            signupPage.clickSignUpWithEmailButton();
            signupPage.clickSignup();
        } catch (TimeoutException e) {
            System.out.println("Unable to click the Sign up button");
        }
        homePage.scrollAndClickLogout();
    }

    private boolean isValidEmail(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        return Pattern.matches(regex, email);
    }

    private boolean isValidPhoneNumber(String phone) {
        String regex = "^07\\d{8}$";
        return Pattern.matches(regex, phone);
    }

    private boolean isStrongPassword(String password) {
        return password.length() >= 8 &&
                password.matches(".*[A-Z].*") &&
                password.matches(".*[0-9].*") &&
                password.matches(".*[!@#$%^&*()_+=|<>?{}\\\\\\\\~-].*");
    }
}
