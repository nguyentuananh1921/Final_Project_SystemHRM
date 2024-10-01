package anhntTest.common;

import anhntTest.PageObjectModel.pages.CommonPage;
import anhntTest.drivers.DriverManager;
import anhntTest.helpers.PropertiesHelper;
import anhntTest.listeners.TestListener;
import anhntTest.constants.ConfigData;
import anhntTest.setupData.Data;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

@Listeners({TestListener.class})
public class BaseTest extends CommonPage {

    @BeforeMethod
    @Parameters({"browser"})
    public void createDriver(@Optional("chrome") String browser, ITestResult iTestResult) {
        if (!ConfigData.BROWSER.isEmpty()) {
            WebDriver driver = setupDriver(ConfigData.BROWSER);
            DriverManager.setDriver(driver);
        } else {
            WebDriver driver = setupDriver(browser);
            DriverManager.setDriver(driver);
        }
        //Gán giá trị driver vào trong ThreadLocal
    }

    public WebDriver setupDriver(String browserName) {
        WebDriver driver;

        switch (browserName.trim().toLowerCase()) {
            case "chrome":
                driver = initChromeDriver();
                break;
            case "firefox":
                driver = initFirefoxDriver();
                break;
            case "edge":
                driver = initEdgeDriver();
                break;
            default:
                System.out.println("Browser: " + browserName + " is invalid, Launching Chrome as browser of choice...");
                driver = initChromeDriver();
        }
        return driver;
    }

    private WebDriver initChromeDriver() {
        System.out.println("Launching Chrome browser...");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        return driver;
    }

    private WebDriver initEdgeDriver() {
        System.out.println("Launching Edge browser...");
        WebDriver driver = new EdgeDriver();
        driver.manage().window().maximize();
        return driver;
    }

    private WebDriver initFirefoxDriver() {
        System.out.println("Launching Firefox browser...");
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();
        return driver;
    }

    @AfterMethod
    public void closeDriver(ITestResult iTestResult) {
        DriverManager.quit();
    }

}
