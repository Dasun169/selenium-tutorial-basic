package org.example.pages;

import org.example.constants.AppConstants;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SignupPage extends BasePage {

    @FindBy(xpath = AppConstants.FIRST_NAME_INPUT)
    private WebElement firstNameInput;

    @FindBy(xpath = AppConstants.LAST_NAME_INPUT)
    private WebElement lastNameInput;

    @FindBy(xpath = AppConstants.EMAIL_REGISTER)
    private WebElement emailInput;

    @FindBy(xpath = AppConstants.MOBILE_NUMBER)
    private WebElement mobileNumber;

    @FindBy(xpath = AppConstants.PASSWORD_REGISTER)
    private WebElement passwordInput;

    @FindBy(xpath = AppConstants.SIGNUP_BUTTON)
    private WebElement signupButton;

    @FindBy(xpath = AppConstants.SIGN_UP_BUTTON_FROM_HAM)
    private WebElement sign_up_from_ham;

    @FindBy(xpath = AppConstants.HAMBURG)
    private WebElement hamburg;

    @FindBy(xpath = AppConstants.SIGN_UP_WITH_EMAIL)
    private WebElement sign_up_with_email;

    @FindBy(xpath = AppConstants.PROFILE_SIDEBAR_XPATH)
    private WebElement profile_section;

    @FindBy(xpath = AppConstants.LOGOUT_LINK_XPATH)
    private WebElement sign_out_button;

    public SignupPage(WebDriver driver) {
        super(driver);
    }

    public void clickSignUpButton(WebDriver driver) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        wait.until(ExpectedConditions.elementToBeClickable(hamburg)).click();
        wait.until(ExpectedConditions.elementToBeClickable(sign_up_from_ham)).click();
        wait.until(ExpectedConditions.elementToBeClickable(sign_up_with_email)).click();
    }

    public void fillSignupForm(WebDriver driver, String firstName, String lastName, String email, String mobile, String password) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            Thread.sleep(1000);
            wait.until(ExpectedConditions.visibilityOf(firstNameInput)).sendKeys(firstName);
            Thread.sleep(1000);

            Thread.sleep(1000);
            wait.until(ExpectedConditions.visibilityOf(lastNameInput)).sendKeys(lastName);
            Thread.sleep(1000);

            Thread.sleep(1000);
            wait.until(ExpectedConditions.visibilityOf(emailInput)).sendKeys(email);
            Thread.sleep(1000);

            Thread.sleep(1000);
            wait.until(ExpectedConditions.visibilityOf(mobileNumber)).sendKeys(mobile);
            Thread.sleep(1000);

            Thread.sleep(1000);
            wait.until(ExpectedConditions.visibilityOf(passwordInput)).sendKeys(password);
            Thread.sleep(1000);
    }

    public void clickSignup() {
        signupButton.click();
    }

    public void scrollAndClickLogout(WebDriver driver) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        Thread.sleep(2000);
        wait.until(ExpectedConditions.elementToBeClickable(hamburg)).click();
        Thread.sleep(2000);

        WebElement profileSectionElement = wait.until(ExpectedConditions.elementToBeClickable(profile_section));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", profileSectionElement);

        WebElement signOutButtonElement = wait.until(ExpectedConditions.elementToBeClickable(sign_out_button));
        signOutButtonElement.click();

        Thread.sleep(10000);
    }
}

