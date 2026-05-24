package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.testng.AllureTestNg;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestContext;
import org.testng.annotations.*;
import pages.CartPage;
import pages.LoginPage;
import pages.ProductsPage;
import utils.TestListener;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import pages.CartPage;
import pages.LoginPage;
import pages.ProductsPage;
import utils.PropertyReader;

import java.util.HashMap;

@Log4j2
@Listeners({AllureTestNg.class, TestListener.class})
public class BaseTest {
    WebDriver driver;
    LoginPage loginPage;
    ProductsPage productsPage;
    CartPage cartPage;
    LocatorsTest locatorsTest;

    protected String user=System.getProperty("user", PropertyReader.getProperty("user"));
    protected String password=System.getProperty("password",PropertyReader.getProperty("password"));

    @Parameters({"browser"})
    @BeforeMethod(alwaysRun = true, description = "Настройка браузера")
    public void init(@Optional("chrome") String browser, ITestContext iTestContext) {
        log.info("init browser");
        if (browser.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();
            HashMap<String, Object> chromePrefs = new HashMap<>();
            chromePrefs.put("credentials_enable_service", false);
            chromePrefs.put("profile.password_manager_enabled", false);
            options.setExperimentalOption("prefs", chromePrefs);
            options.addArguments("--incognito");
            options.addArguments("--disable-notifications");
            options.addArguments("--disable-popup-blocking");
            options.addArguments("--disable-infobars");
            options.addArguments("--headless");
            driver = new ChromeDriver(options);
        } else if (browser.equalsIgnoreCase("edge")) {
            EdgeOptions options=new EdgeOptions();
            options.addArguments("--headless");
            driver = new EdgeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
           FirefoxOptions options=new FirefoxOptions();
           options.addArguments("--headless");
            driver = new FirefoxDriver();
        }
        loginPage = new LoginPage(driver);
        productsPage = new ProductsPage(driver);
        cartPage = new CartPage(driver);
        iTestContext.setAttribute("driver", driver);
    }

    @Description("Закрытие браузера")
    @Step("Закрытие браузера")
    @AfterMethod(alwaysRun = true, description = "Закрытие браузера")
    public void tearDawn() {
        driver.quit();
    }
}
