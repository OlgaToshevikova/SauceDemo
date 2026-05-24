package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;

@Log4j2
public abstract class BasePage {
    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    WebDriver driver;
    public final String BASE_URL = "https://www.saucedemo.com/";

    @Step("Настройка браузера")
    public LoginPage open() {
        log.info("Opening LoginPage");
        driver.get(BASE_URL);
        return new LoginPage(driver);
    }

    public abstract BasePage isPageOpened();
}
