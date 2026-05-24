package tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
/*
 3. Создать отдельный Java-класс с тестом, сценарий:
  a. Залогиниться
   b. Добавить товар в корзину
   c. Перейти в корзину
   d. Проверить (assertEquals) стоимость товара и его имя в корзине
 */

public class CartTest extends BaseTest {
    @Test(testName = "Добавление/удаление товара из корзины",
            description = "Пользовательский путь добавления/удаления товара из корзины")
    public void checkCart() {
        SoftAssert softAssert = new SoftAssert();
        loginPage.open();
        loginPage.login(user, password);
        productsPage.addIoCart();//Добавляем рюкзак в корзину
        cartPage.open();//Открываем страницу корзины
        softAssert.assertEquals(cartPage.getItem(), "Sauce Labs Backpack");//Проверяем, что в корзине есть рюкзак
        cartPage.removeFromCart();//Удаляем рюкзак из корзины
        softAssert.assertTrue(cartPage.checkIsCartEmpty());
        softAssert.assertAll();
    }
}
