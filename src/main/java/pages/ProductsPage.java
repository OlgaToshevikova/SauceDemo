package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@Log4j2
public class ProductsPage extends BasePage {

    private final By TITLE = By.cssSelector("[data-test=title]");
    private final String ADD_TO_CART_PATTERN =
            "//*[text()='%s']/ancestor::div[@class='inventory_item']//button[text()='Add to cart']";
    private final String REMOVE_PATTERN =
            "//*[text()='%s']/ancestor::div[@class='inventory_item']//button[text()='Remove']";
    private final By ITEM = By.cssSelector("[class=inventory_item]");

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

    @Step("Открываем страницу с продуктами")
    public ProductsPage openProductPage() {
        log.info("Opening ProductsPage");
        driver.get(BASE_URL + "inventory.html");
        return this;
    }

    public ProductsPage isPageOpened() {
        log.info("Check ProductsPage is opened");
        wait.until(ExpectedConditions.visibilityOfElementLocated(TITLE));
        return this;
    }

    @Step("Проверяем, что страница открылась")
    public String getTitle() {
        return driver.findElement(TITLE).getText();
    }

    @Step("Добавляем товар '{product}' в корзину")
    public ProductsPage addIoCart(String product) {
        log.info("Add an item to Cart");
        driver.findElement(By.xpath(String.format(ADD_TO_CART_PATTERN, product))).click();
        return this;
    }

    @Step("Количество товаров на странице")
    public int checkAmountOfItemsOnPage() {
        log.info("Amound of items is driver.findElements(ITEM).size()");
        return driver.findElements(ITEM).size();
    }

    @Step("Проверяем изменение кнопки 'Add to Cart'")
    public String checkChangesOfButtonAddToCart(String product) {
        log.info("Check changing of button 'Add to Cart' to 'Remove'");
        driver.findElement(By.xpath(String.format(ADD_TO_CART_PATTERN, product))).click();
        return driver.findElement(By.xpath(String.format(REMOVE_PATTERN, product))).getText();
    }

    @Step("Проверяем изменение кнопки 'Remove'")
    public String checkChangesOfButtonRemove(String product) {
        log.info("Check changing of button 'Remove' to 'Add to Cart'");
        driver.findElement(By.xpath(String.format(REMOVE_PATTERN, product))).click();
        return driver.findElement(By.xpath(String.format(ADD_TO_CART_PATTERN, product))).getText();
    }
}
