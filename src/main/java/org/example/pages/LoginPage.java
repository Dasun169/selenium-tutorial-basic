package org.example.pages;

import org.example.utils.constants.LoginPageConstants;
import utils.ReportStatus;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    // Locators
    private static final By EMAIL_LOGIN = By.xpath("//input[@id='emailField']");
    private static final By PASSWORD_LOGIN = By.xpath("//input[@id='pwFiled']");
    private static final By LOGIN_BUTTON = By.xpath("//button[@id='login-button']");
    private static final By ERROR_MESSAGE_HEADER = By.xpath("//div[@class='alert alert-danger' and @role='alert']");
    private static final By SIGN_UP_LINK = By.xpath("//a[text()='Sign up']");
    private static final By LBL_LOGIN_HEADING = By.xpath("//div[@class='login_box']/h3");
    private static final By LBL_USERNAME_ERR = By.xpath("//input[@id='emailField']/following-sibling::p");
    private static final By LBL_PASSWORD_ERR = By.xpath("//input[@id='pwFiled']/following-sibling::p");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void enterEmail(String email) {
        waitForElementToVisible(EMAIL_LOGIN).sendKeys(email);
        logStep("Entered email into login field", ReportStatus.INFO, false);
    }

    public void enterPassword(String password) {
        waitForElementToVisible(PASSWORD_LOGIN).sendKeys(password);
        logStep("Entered password into login field", ReportStatus.INFO, false);
    }

    public void clickLogin() {
        clickOnElement(LOGIN_BUTTON);
        logStep("Clicked the login button", ReportStatus.INFO, true);
    }

    public boolean isErrorMessageDisplayed() {
        return isLocatorVisible(ERROR_MESSAGE_HEADER);
    }

    public void clickSignUpLink() {
        clickOnElement(SIGN_UP_LINK);
    }

    public void loginPageValidation() {
        waitForElementToBeVisible(LBL_LOGIN_HEADING, SHORT_TIMEOUT);
        validateElementText(LBL_LOGIN_HEADING, LoginPageConstants.LOGIN_HEADING);
        logStep("Verified login page heading is displayed", ReportStatus.PASS, true);
    }

    public void invalidPasswordValidation() {
        validateElementText(ERROR_MESSAGE_HEADER, LoginPageConstants.INVALID_LOGIN_ERR_MSG);
        logStep("Verified invalid password error message", ReportStatus.PASS, true);
    }

    public void invalidUserNameValidation() {
        validateElementText(LBL_USERNAME_ERR, LoginPageConstants.INVALID_EMAIL_ERR_MSG);
        logStep("Verified invalid username error validation", ReportStatus.PASS, true);
    }

    public void EmptyUserNamePasswordValidation() {
        validateElementText(LBL_USERNAME_ERR, LoginPageConstants.EMPTY_EMAIL_ERR_MSG);
        validateElementText(LBL_PASSWORD_ERR, LoginPageConstants.EMPTY_PASSWORD_ERR_MSG);
        logStep("Verified empty username and password validation errors", ReportStatus.PASS, true);
    }

    public void navigateToLogin() {

    }
}
