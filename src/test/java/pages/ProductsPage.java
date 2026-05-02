package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
public class ProductsPage extends BasePage {

    private final By TITLE = By.cssSelector("[data-test=title]");
    private final By BTN_ADD_TO_CART_BACKPACK = By.xpath("//button[@name='add-to-cart-sauce-labs-backpack']");
    private final By BTN_REMOVE_BACKPACK = By.id("remove-sauce-labs-backpack");
    private final By ITEM = By.cssSelector("[class=inventory_item]");

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get(BASE_URL + "inventory.html");
    }

    public String getTitle() {
        return driver.findElement(TITLE).getText();
    }

    public void addIoCart() {
        driver.findElement(BTN_ADD_TO_CART_BACKPACK).click();
    }

    public int checkAmountOfItemsOnPage() {
        return driver.findElements(ITEM).size();
    }

    public String checkChangesOfButtonAddToCart() {
        driver.findElement(BTN_ADD_TO_CART_BACKPACK).click();
        return driver.findElement(BTN_REMOVE_BACKPACK).getText();
    }
    public String checkChangesOfButtonRemove() {
        driver.findElement(BTN_REMOVE_BACKPACK).click();
        return driver.findElement(BTN_ADD_TO_CART_BACKPACK).getText();
    }
}
