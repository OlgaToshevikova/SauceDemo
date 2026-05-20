package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

@Log4j2
public class CartPage extends BasePage {

    private final By BACKPACK_ITEM = (By.xpath("//div[@class='inventory_item_name']"));
    private final By BTN_REMOVE = (By.xpath("//button[@name='remove-sauce-labs-backpack']"));
    private final By TITLE = (By.xpath("//*[text()='Products']"));


    public CartPage(WebDriver driver) {
        super(driver);
    }

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

    @Step("Открытие страницы корзины")
    public CartPage openCartPage() {
        log.info("Opening CartPage");
        driver.get(BASE_URL + "cart.html");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        return this;
    }

    public CartPage isPageOpened() {
        log.info("Check opened CartPage");
        wait.until(ExpectedConditions.visibilityOfElementLocated(TITLE));
        return this;
    }

    @Step("Выбор товара на странице")
    public String getItem() {
        log.info("Picking an item 'backPack'");
        return driver.findElement(BACKPACK_ITEM).getText();
    }

    @Step("Удаление товара из корзины")
    public CartPage removeFromCart() {
        log.info("Remove the item from the Cart");
        driver.findElement((BTN_REMOVE)).click();
        return this;
    }

    @Step("Проверяем, что корзина пуста после удаления товара")
    public boolean checkIsCartEmpty() {
        log.info("Cart is empty");
        List<WebElement> items = driver.findElements(By.cssSelector("Backpack"));
        return items.isEmpty();
    }
}
