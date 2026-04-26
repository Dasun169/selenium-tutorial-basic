package base;

import org.example.pages.BasePage;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class BaseTest {
    protected WebDriver driver;
    protected Properties properties;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        loadProperties();

        // Read configuration values (Command Line overrides config.properties)
        String browser = System.getProperty("browser", properties.getProperty("browser", "chrome")).toLowerCase();
        boolean headless = Boolean.parseBoolean(System.getProperty("headless", properties.getProperty("headless", "false")));
        int width = Integer.parseInt(System.getProperty("windowWidth", properties.getProperty("windowWidth", "1920")));
        int height = Integer.parseInt(System.getProperty("windowHeight", properties.getProperty("windowHeight", "1080")));

        // Initialize driver based on browser type
        switch (browser) {
            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                if (headless)
                    firefoxOptions.addArguments("--headless");
                driver = new FirefoxDriver(firefoxOptions);
                break;
            case "edge":
            case "webkit": // Mapping webkit to Edge for Windows (WebKit/Safari is macOS only in Selenium)
                EdgeOptions edgeOptions = new EdgeOptions();
                if (headless)
                    edgeOptions.addArguments("--headless");
                driver = new EdgeDriver(edgeOptions);
                break;
            case "chrome":
            default:
                ChromeOptions chromeOptions = new ChromeOptions();
                if (headless)
                    chromeOptions.addArguments("--headless");
                driver = new ChromeDriver(chromeOptions);
                break;
        }

        // Apply window size and navigation
        driver.manage().window().setSize(new Dimension(width, height));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(BasePage.DEFAULT_TIMEOUT));
        driver.get(BasePage.BASE_URL);
    }

    private void loadProperties() {
        properties = new Properties();
        try {
            FileInputStream file = new FileInputStream("src/test/resources/config.properties");
            properties.load(file);
        } catch (IOException e) {
            System.out.println("Could not load config.properties. Using default settings.");
        }
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
