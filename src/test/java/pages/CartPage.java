package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class CartPage extends BasePage {

    private final By BACKPACK_ITEM = (By.xpath("//div[@class='inventory_item_name']"));
    private final By BTN_REMOVE = (By.xpath("//button[@name='remove-sauce-labs-backpack']"));

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get(BASE_URL + "cart.html");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    public String getItem() {
        return driver.findElement(BACKPACK_ITEM).getText();
    }

    public void removeFromCart() {
        driver.findElement(BTN_REMOVE).click();
    }

    public boolean checkIsCartEmpty() {
        List<WebElement> items = driver.findElements(By.cssSelector("Backpack"));
        return items.isEmpty();
    }
}
