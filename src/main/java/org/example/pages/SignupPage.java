package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SignupPage extends BasePage {

    // Locators
    private static final By FIRST_NAME_INPUT = By.xpath("//input[@id='fname']");
    private static final By LAST_NAME_INPUT = By.xpath("//input[@id='lanme']");
    private static final By EMAIL_REGISTER = By.xpath("//input[@id='email']");
    private static final By MOBILE_NUMBER = By.xpath("//input[@id='mobileNum']");
    private static final By PASSWORD_REGISTER = By.xpath("//input[@id='pw']");
    private static final By SIGNUP_BUTTON = By.xpath("//button[@id='submit']");
    private static final By SIGN_UP_WITH_EMAIL = By.xpath("//a[@id='continue_with_email_signup']");

    public SignupPage(WebDriver driver) {
        super(driver);
    }

    public void fillSignupForm(String firstName, String lastName, String email, String mobile,
            String password) {
        waitForElementToVisible(FIRST_NAME_INPUT).sendKeys(firstName);
        waitForElementToVisible(LAST_NAME_INPUT).sendKeys(lastName);
        waitForElementToVisible(EMAIL_REGISTER).sendKeys(email);
        waitForElementToVisible(MOBILE_NUMBER).sendKeys(mobile);
        waitForElementToVisible(PASSWORD_REGISTER).sendKeys(password);
    }

    public void clickSignUpWithEmailButton() {
        clickOnElement(SIGN_UP_WITH_EMAIL);
    }

    public void clickSignup() {
        clickOnElement(SIGNUP_BUTTON);
    }
}
