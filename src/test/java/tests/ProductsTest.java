package tests;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProductsTest extends BaseTest {
    @Test
    public void checkProductsPage() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        Assert.assertEquals(productsPage.checkAmountOfItemsOnPage(), 6);
    }

    public void checkChangingButtonAddTOCart() {
        Assert.assertEquals(productsPage.checkChangesOfButtonAddToCart(), "Remove");
    }

    public void checkChangingButtonRemove() {
        Assert.assertEquals(productsPage.checkChangesOfButtonRemove(), "Add to cart");
    }
}
