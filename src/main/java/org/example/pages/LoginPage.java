package org.example.pages;

import org.example.constants.AppConstants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage extends BasePage {

    @FindBy(xpath = AppConstants.EMAIL_LOGIN)
    private WebElement emailInput;

    @FindBy(xpath = AppConstants.PASSWORD_LOGIN)
    private WebElement passwordInput;

    @FindBy(xpath = AppConstants.LOGIN_BUTTON)
    private WebElement loginButton;

    @FindBy(xpath = AppConstants.ERROR_MESSAGE)
    private WebElement errorMessage;

    @FindBy(xpath = AppConstants.HAMBURG)
    private WebElement hamburg;

    @FindBy(xpath = AppConstants.LOGIN_BUTTON_FROM_HAM)
    private WebElement loginFromHam;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void clickLoginButton(WebDriver driver) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        wait.until(ExpectedConditions.elementToBeClickable(hamburg)).click();
        wait.until(ExpectedConditions.elementToBeClickable(loginFromHam)).click();
    }

    public void enterEmail(String email) {
        emailInput.sendKeys(email);
    }

    public void enterPassword(String password) {
        passwordInput.sendKeys(password);
    }

    public void clickLogin() {
        loginButton.click();
    }

    public boolean isErrorMessageDisplayed() {
        return errorMessage.isDisplayed();
    }
}
