import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.time.Duration;
import java.util.HashMap;
/*
 3. Создать отдельный Java-класс с тестом, сценарий:
  a. Залогиниться
   b. Добавить товар в корзину
   c. Перейти в корзину
   d. Проверить (assertEquals) стоимость товара и его имя в корзине
 */

public class CartTest {
    @Test
    public void checkCart() {
        ChromeOptions options = new ChromeOptions();
        HashMap<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("credentials_enable_service", false);
        chromePrefs.put("profile.password_manager_enabled", false);
        options.setExperimentalOption("prefs", chromePrefs);
        options.addArguments("--incognito");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-infobars");
        WebDriver driver = new ChromeDriver(options);
        driver.get("https://www.saucedemo.com/");
        //Логинимся
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.name("password")).sendKeys("secret_sauce");
        driver.findElement(By.className("submit-button")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        //Добавляем рюкзак в корзину
        driver.findElement(By.xpath("//button[@name='add-to-cart-sauce-labs-backpack']")).click();
        //Открываем корзину
        driver.findElement(By.xpath("//a[@class='shopping_cart_link']")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        //проверяем наличие рюкзака в корзине
        String text = driver.findElement(By.xpath("//div[@class='inventory_item_name']")).getText();
        Assert.assertEquals(text, "Sauce Labs Backpack");
        //Удаляем товар из корзины
        driver.findElement(By.xpath("//button[@name='remove-sauce-labs-backpack']")).click();
        driver.quit();
    }
}
