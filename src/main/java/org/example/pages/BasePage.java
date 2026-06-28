package org.example.pages;

import org.example.utils.constants.BasePageConstants;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ExtentReportManager;
import utils.ReportStatus;

import java.time.Duration;
import java.util.List;

public class BasePage {
    public static final String BASE_URL = BasePageConstants.COG_BASE_URL;
    public static final int DEFAULT_TIMEOUT = 10;
    public static final int SHORT_TIMEOUT = 5;
    public static final int LONG_TIMEOUT = 20;
    public static final int MODERATE_TIMEOUT = 15;

    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
    }

    public WebElement waitForElementToVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void waitForElementToBeVisible(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected void waitForElementToBeVisible(By locator, int timeoutInSeconds) {
        WebDriverWait customWait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        customWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected boolean isLocatorVisible(By locator) {
        try {
            return waitForElementToVisible(locator).isDisplayed();
        } catch (TimeoutException | NoSuchElementException | StaleElementReferenceException e) {
            return false;
        }
    }

    protected void clickOnElement(By locator) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        try {
            element.click();
        } catch (ElementClickInterceptedException e) {
            // Fallback to JavaScript click when element is intercepted by sticky/floating elements like phpdebugbar
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }

    protected void scrollToWebElement(By locator) {
        WebElement element = waitForElementToVisible(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    protected void mouseHover(By locator) {
        WebElement element = waitForElementToVisible(locator);
        new Actions(driver).moveToElement(element).perform();
    }

    protected void logStep(String description, ReportStatus status, boolean screenshot) {
        ExtentReportManager.addReportEntry(description, status, screenshot, driver);
    }

    protected boolean isElementsPresentBy(By locator) {
        List<WebElement> elements = driver.findElements(locator);
        return !elements.isEmpty();
    }

    protected boolean isElementPresentBy(By locator) {
        return isElementsPresentBy(locator);
    }

    protected String getTextFromElement(By locator) {
        return waitForElementToVisible(locator).getText();
    }

    protected void validateElementText(By locator, String expectedText) {
        waitForElementToVisible(locator);

        String actualText = driver.findElement(locator).getText().trim();

        if (!actualText.equals(expectedText)) {
            throw new AssertionError(
                    String.format(
                            "Text validation failed.%nExpected: '%s'%nActual: '%s'",
                            expectedText,
                            actualText
                    )
            );
        }
    }
}
