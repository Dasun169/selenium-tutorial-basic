package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.example.utils.constants.HomePageConstants;
import utils.ReportStatus;

public class HomePage extends BasePage {

    // Locators
    private static final By COG_LOGO = By.xpath("//a[@title='Cloud of Goods']");
    private static final By MOBILITY_SCOOTOR_SECTION = By.xpath("(//div[@class='inner-shadow'])[1]");
    private static final By HAMBURG = By.xpath("//button[@id='HeaderLinksDesktopMenu']");
    private static final By LOGIN_BUTTON_FROM_HAM = By.xpath("//a[@id='HeaderLinksLogin1']");
    private static final By PROFILE_SIDEBAR = By.xpath("//div[@class='n-s-a accordion']");
    private static final By LOGOUT_LINK = By.xpath("//a[@id='SideBarLinkSign']");
    private static final By HEADER_HOME = By.xpath("//div[@class='header-text']/h1");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void homePageValidation() {
        waitForElementToBeVisible(HEADER_HOME);
        validateElementText(HEADER_HOME, HomePageConstants.LOGIN_HEADING);
        logStep("Verified home page heading is visible", ReportStatus.PASS, false);
    }

    public void clickLoginButton() {
        waitForElementToBeVisible(HAMBURG, SHORT_TIMEOUT);
        clickOnElement(HAMBURG);
        waitForElementToBeVisible(LOGIN_BUTTON_FROM_HAM, SHORT_TIMEOUT);
        clickOnElement(LOGIN_BUTTON_FROM_HAM);
        logStep("Clicked login button from the hamburger menu", ReportStatus.INFO, true);
    }

    public void scrollAndClickLogout() {
        scrollToWebElement(PROFILE_SIDEBAR);
        clickOnElement(LOGOUT_LINK);

        System.out.println("[Mock] Simulating click on hamburger menu...");
        System.out.println("[Mock] Simulating scrolling to profile section...");
        System.out.println("[Mock] Simulating click on sign out button...");

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Mock logout interrupted.");
        }

        System.out.println("[Mock] Successfully executed mock logout.");
    }

    public void navigateToHome() {
        waitForElementToBeVisible(COG_LOGO);
        clickOnElement(COG_LOGO);
        waitForElementToBeVisible(HEADER_HOME, MODERATE_TIMEOUT);
        logStep("Navigated back to the home page", ReportStatus.INFO, false);
    }
}
