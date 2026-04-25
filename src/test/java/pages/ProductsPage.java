package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductsPage extends BasePage {


    private final By TITLE= By.cssSelector("[data-test=title]");
    private final By BTN_ADD_TO_CART=By.xpath("//button[@name='add-to-cart-sauce-labs-backpack']");

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public void open(){
       driver.get(BASE_URL+"inventory.html");
    }

    public String getTitle(){
        return driver.findElement(TITLE).getText();
    }
    public void addIoCart(){
        driver.findElement(BTN_ADD_TO_CART).click();
    }

}
