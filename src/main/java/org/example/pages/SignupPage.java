package org.example.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SignupPage extends BasePage {

    // Locators
    private static final String FIRST_NAME_INPUT = "//input[@id=\"fname\"]";
    private static final String LAST_NAME_INPUT = "//input[@id=\"lanme\"]";
    private static final String EMAIL_REGISTER = "//input[@id=\"email\"]";
    private static final String MOBILE_NUMBER = "//input[@id=\"mobileNum\"]";
    private static final String PASSWORD_REGISTER = "//input[@id=\"pw\"]";
    private static final String SIGNUP_BUTTON = "//button[@id=\"submit\"]";
    private static final String HAMBURG = "//button[@id='HeaderLinksDesktopMenu']";
    private static final String SIGN_UP_BUTTON_FROM_HAM = "//a[@id='HeaderLinksLogin2']";
    private static final String SIGN_UP_WITH_EMAIL = "//a[@id='continue_with_email_signup']";
    private static final String PROFILE_SIDEBAR_XPATH = "//div[@class='n-s-a accordion']";
    private static final String LOGOUT_LINK_XPATH = "//a[@id='SideBarLinkSign']";

    @FindBy(xpath = FIRST_NAME_INPUT)
    private WebElement firstNameInput;

    @FindBy(xpath = LAST_NAME_INPUT)
    private WebElement lastNameInput;

    @FindBy(xpath = EMAIL_REGISTER)
    private WebElement emailInput;

    @FindBy(xpath = MOBILE_NUMBER)
    private WebElement mobileNumber;

    @FindBy(xpath = PASSWORD_REGISTER)
    private WebElement passwordInput;

    @FindBy(xpath = SIGNUP_BUTTON)
    private WebElement signupButton;

    @FindBy(xpath = SIGN_UP_BUTTON_FROM_HAM)
    private WebElement sign_up_from_ham;

    @FindBy(xpath = HAMBURG)
    private WebElement hamburg;

    @FindBy(xpath = SIGN_UP_WITH_EMAIL)
    private WebElement sign_up_with_email;

    @FindBy(xpath = PROFILE_SIDEBAR_XPATH)
    private WebElement profile_section;

    @FindBy(xpath = LOGOUT_LINK_XPATH)
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

    public void fillSignupForm(WebDriver driver, String firstName, String lastName, String email, String mobile,
            String password) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.visibilityOf(firstNameInput)).sendKeys(firstName);
        wait.until(ExpectedConditions.visibilityOf(lastNameInput)).sendKeys(lastName);
        wait.until(ExpectedConditions.visibilityOf(emailInput)).sendKeys(email);
        wait.until(ExpectedConditions.visibilityOf(mobileNumber)).sendKeys(mobile);
        wait.until(ExpectedConditions.visibilityOf(passwordInput)).sendKeys(password);
    }

    public void clickSignup() {
        signupButton.click();
    }

    public void scrollAndClickLogout(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.elementToBeClickable(hamburg)).click();

        WebElement profileSectionElement = wait.until(ExpectedConditions.visibilityOf(profile_section));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", profileSectionElement);

        WebElement signOutButtonElement = wait.until(ExpectedConditions.elementToBeClickable(sign_out_button));
        signOutButtonElement.click();
    }
}
