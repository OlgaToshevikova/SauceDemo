package pages;
import org.openqa.selenium.WebDriver;

public class BasePage {
    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    WebDriver driver;
    public final String BASE_URL = "https://www.saucedemo.com/";

    public void open() {
        driver.get(BASE_URL);
    }
}
