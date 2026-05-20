package tests;

import io.qameta.allure.*;
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

    @Test(testName = "Добавление/удаление товара из корзины")
    @Epic("E2E")
    @Feature("checkCartUserRoute")
    @Story("Positive CartAction")
    @Severity(SeverityLevel.CRITICAL)
    @Link("https://github.com/allure-framework/allure-maven")
    @TmsLink("NGR-147")
    @Issue("BFR-475")
    @Flaky
    @Owner("Я")
    @Description("Добавление/удаление товара из корзины")
    public void checkCart() {
        SoftAssert softAssert = new SoftAssert();
        loginPage.openLoginPage()
                .isPageOpened()
                .login("standard_user", "secret_sauce")
                .addIoCart("Sauce Labs Backpack");//Добавляем рюкзак в корзину
        cartPage.openCartPage();
        softAssert.assertEquals(cartPage.getItem(), "Sauce Labs Backpack");//Проверяем, что в корзине есть рюкзак
        cartPage.removeFromCart();//Удаляем рюкзак из корзины
        softAssert.assertTrue(cartPage.checkIsCartEmpty());
        softAssert.assertAll();
    }
}
