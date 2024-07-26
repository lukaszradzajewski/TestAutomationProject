package main;
import main.buildSettings.TestEnvironment;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

/**
 * Test_Automation-automationexercise
 *
 * @author lukasz.radzajewski
 **/

public class DriverFactory extends TestEnvironment {

    protected WebDriver driver;
    private static final List<WebDriver> storedDrivers = new ArrayList<>();
    private static final ThreadLocal<WebDriver> drivers = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return drivers.get();
    }

    private void addDriver(WebDriver driver) {
        storedDrivers.add(driver);
        drivers.set(driver);
    }

    public static String getTestsExecutor() {
        String getTestsExecutor = System.getProperty("tests.executor");
        if (getTestsExecutor == null) {
            getTestsExecutor = System.getenv("tests.executor");
            if (getTestsExecutor == null) {
                getTestsExecutor = DEFAULT_TESTS_EXECUTOR;
            }
        }
        return getTestsExecutor;
    }

    protected void startBrowser() {
        displayWebDriverManagerBrowsersVersions(false);
        switch (getTestsExecutor().toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                addDriver(driver = new ChromeDriver(chromeOptions));
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                addDriver(driver = new FirefoxDriver(firefoxOptions));
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                EdgeOptions edgeOptions = new EdgeOptions();
                addDriver(driver = new EdgeDriver(edgeOptions));
                break;
            case "ie":
                WebDriverManager.iedriver().setup();
                InternetExplorerOptions internetExplorerOptions = new InternetExplorerOptions();
                addDriver(driver = new InternetExplorerDriver(internetExplorerOptions));
                break;
            case "safari":
                SafariOptions safariOptions = new SafariOptions();
                addDriver(driver = new SafariDriver(safariOptions));
                break;
            default:
                throw new IllegalStateException("This browser isn't supported yet! Sorry...");
        }
        //NOT THE BEST PRACTICE TO MIX IMPLICITLY WITH EXPLICITLY WAIT
        //getDriver().manage().timeouts().implicitlyWait(Timeouts.FIND_ELEMENT_TIMEOUT.value, TimeUnit.SECONDS);
        getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Timeouts.PAGE_LOAD_TIMEOUT.value));
        getDriver().manage().timeouts().scriptTimeout(Duration.ofSeconds(Timeouts.SCRIPT_TIMEOUT.value));
        getDriver().manage().deleteAllCookies();
        getDriver().manage().window().maximize();
    }

    protected void destroyDriver() {
        for (WebDriver driver : storedDrivers) {
            if (driver != null) {
                driver.quit();
            }
        }
    }
}