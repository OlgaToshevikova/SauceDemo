package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@Log4j2
public class LoginPage extends BasePage {

    private final By USERNAME_FIELD = (By.id("user-name"));
    private final By PASSWORD_FIELD = (By.name("password"));
    private final By LOGIN_BUTTON = (By.className("submit-button"));
    private final By ERROR_MESSAGE = By.xpath("//*[@data-test='error']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

    @Step("Открываем странице логина")
    public LoginPage openLoginPage() {
        log.info("Opening LoginPage");
        driver.get(BASE_URL);
        return this;
    }

    public LoginPage isPageOpened() {
        log.info("Checking LoginPage is opened");
        wait.until(ExpectedConditions.visibilityOfElementLocated(USERNAME_FIELD));
        return this;
    }

    @Step("Вход в систему с корректными кредами")
    public ProductsPage login(String user, String password) {
        log.info("Log in with name'{}' and password'{}'", user, password);
        driver.findElement(USERNAME_FIELD).sendKeys(user);
        driver.findElement(PASSWORD_FIELD).sendKeys(password);
        driver.findElement(LOGIN_BUTTON).click();
        return new ProductsPage(driver);
    }

    @Step("Проверяем сообщение об ошибке при некорректных кредах")
    public String getErrorMessage() {
        log.error(driver.findElement(ERROR_MESSAGE).getText());
        return driver.findElement(ERROR_MESSAGE).getText();
    }
}
