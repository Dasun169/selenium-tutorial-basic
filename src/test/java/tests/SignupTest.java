package tests;

import base.BaseTest;
import org.example.pages.SignupPage;
import org.openqa.selenium.TimeoutException;
import org.testng.annotations.Test;
import java.util.Random;
import java.util.regex.Pattern;

public class SignupTest extends BaseTest {
    public static String signupEmail;
    public static String signupPassword;
    public static String phoneNumber;

    @Test
    public void testSuccessfulSignup() throws InterruptedException {
        SignupPage signupPage = new SignupPage(driver);
        signupPage.clickSignUpButton(driver);

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

        signupPage.fillSignupForm(driver,"John", "Doe", signupEmail, phoneNumber, signupPassword);
        try{
            signupPage.clickSignup();
        }catch (TimeoutException e){
            System.out.println("Unable to click the Sign up button");
        }
        signupPage.scrollAndClickLogout(driver);
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
                password.matches(".*[!@#$%^&*()_+=|<>?{}\\\\[\\\\]~-].*");
    }
}
