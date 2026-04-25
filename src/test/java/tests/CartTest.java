package tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.time.Duration;
/*
 3. Создать отдельный Java-класс с тестом, сценарий:
  a. Залогиниться
   b. Добавить товар в корзину
   c. Перейти в корзину
   d. Проверить (assertEquals) стоимость товара и его имя в корзине
 */

public class CartTest extends BaseTest{
    @Test
    public void checkCart() {

loginPage.open();
loginPage.login("standard_user","secret_sauce");
productsPage.addIoCart();
cartPage.open();


//        driver.get("https://www.saucedemo.com/");
//        //Логинимся
//        driver.findElement(By.id("user-name")).sendKeys("standard_user");
//        driver.findElement(By.name("password")).sendKeys("secret_sauce");
//        driver.findElement(By.className("submit-button")).click();
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
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
