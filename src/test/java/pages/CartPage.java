package pages;

import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class CartPage extends BasePage {

    public CartPage(WebDriver driver) {
        super(driver);


    }
    public void open(){
        driver.get(BASE_URL+"cart.html");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }
}
