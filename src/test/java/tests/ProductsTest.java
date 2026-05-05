package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ProductsTest extends BaseTest {
    @Test(testName = "Количество продуктов на странице",
            description = "Количество продуктов на странице"
    )
    public void checkProductsPage() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        Assert.assertEquals(productsPage.checkAmountOfItemsOnPage(), 6);
    }

    @Test(testName = "Кнопка \"Add to Cart\" при нажатии",
            description = "Изменение кнопки \"Add to Cart\" при добавлении товара в корзину"
    )
    public void checkChangingButtonAddTOCart() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        Assert.assertEquals(productsPage.checkChangesOfButtonAddToCart(), "Remove");
    }

    @Test(testName = "Кнопка \"Remove\" при нажатии",
            description = "Изменение кнопки \"Remove\" при удалении товара из корзины"
    )
    public void checkChangingButtonRemove() {
        loginPage.open();
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addIoCart();
        Assert.assertEquals(productsPage.checkChangesOfButtonRemove(), "Add to cart");
    }
}
