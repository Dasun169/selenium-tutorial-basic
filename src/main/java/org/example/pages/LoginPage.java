package org.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage extends BasePage {

    // Locators
    private static final String EMAIL_LOGIN = "//input[@id='emailField']";
    private static final String PASSWORD_LOGIN = "//input[@id='pwFiled']";
    private static final String LOGIN_BUTTON = "//button[@id='login-button']";
    private static final String ERROR_MESSAGE = "//div[@class='alert alert-danger' and @role='alert']";
    private static final String HAMBURG = "//button[@id='HeaderLinksDesktopMenu']";
    private static final String LOGIN_BUTTON_FROM_HAM = "//a[@id='HeaderLinksLogin1']";

    @FindBy(xpath = EMAIL_LOGIN)
    private WebElement emailInput;

    @FindBy(xpath = PASSWORD_LOGIN)
    private WebElement passwordInput;

    @FindBy(xpath = LOGIN_BUTTON)
    private WebElement loginButton;

    @FindBy(xpath = ERROR_MESSAGE)
    private WebElement errorMessage;

    @FindBy(xpath = HAMBURG)
    private WebElement hamburg;

    @FindBy(xpath = LOGIN_BUTTON_FROM_HAM)
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
