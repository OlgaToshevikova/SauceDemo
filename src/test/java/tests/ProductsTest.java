package tests;

import io.qameta.allure.*;
import lombok.extern.log4j.Log4j2;
import org.testng.Assert;
import org.testng.annotations.Test;

@Log4j2
public class ProductsTest extends BaseTest {
    @Test(testName = "Количество продуктов на странице",
            description = "Количество продуктов на странице"
    )

    @Description("Количество продуктов на странице")
    @Epic("E2E")
    @Feature("checkAmountofProducts")
    @Story("Positive Check")
    @Severity(SeverityLevel.CRITICAL)
    @Link("https://github.com/allure-framework/allure-maven")
    @TmsLink("NGR-148")
    @Issue("BFR-471")
    @Owner("Я")
    public void checkProductsPage() {
        loginPage.openLoginPage();
        loginPage.login("standard_user", "secret_sauce");
        loginPage.open();
        loginPage.login(user, password);
        Assert.assertEquals(productsPage.checkAmountOfItemsOnPage(), 6);
    }

    @Test(testName = "Кнопка \"Add to Cart\" при нажатии",
            description = "Изменение кнопки \"Add to Cart\" при добавлении товара в корзину"
    )
    @Description("Изменение кнопки \"Add to Cart\" при добавлении товара в корзину")
    @Epic("E2E")
    @Feature("ChangingButtonAddTOCart")
    @Story("Positive Check")
    @Severity(SeverityLevel.CRITICAL)
    @Link("https://github.com/allure-framework/allure-maven")
    @TmsLink("NGR-148")
    @Owner("Я")
    public void checkChangingButtonAddTOCart() {
        loginPage.openLoginPage()
                .isPageOpened()
                .login("standard_user", "secret_sauce");

        Assert.assertEquals(productsPage.checkChangesOfButtonAddToCart("Sauce Labs Backpack"), "Remove");
        loginPage.open();
        loginPage.login(user, password);
        Assert.assertEquals(productsPage.checkChangesOfButtonAddToCart(), "Remove");
    }

    @Test(testName = "Кнопка \"Remove\" при нажатии",
            description = "Изменение кнопки \"Remove\" при удалении товара из корзины"
    )
    @Description("Изменение кнопки \"Remove\" при удалении товара из корзины")
    @Epic("E2E")
    @Feature("ChangingButtonRemove")
    @Story("Positive Check")
    @Severity(SeverityLevel.CRITICAL)
    @Link("https://github.com/allure-framework/allure-maven")
    @TmsLink("NGR-149")
    @Owner("Я")
    public void checkChangingButtonRemove() {
        log.info("check Changing Button Remove");
        loginPage.openLoginPage()
                .login("standard_user", "secret_sauce")
                .addIoCart("Sauce Labs Backpack");
        Assert.assertEquals(productsPage.checkChangesOfButtonRemove("Sauce Labs Backpack"), "Add to cart");
        loginPage.open();
        loginPage.open();
        loginPage.login(user, password);
        productsPage.addIoCart();
        Assert.assertEquals(productsPage.checkChangesOfButtonRemove(), "Add to cart");
    }
}
