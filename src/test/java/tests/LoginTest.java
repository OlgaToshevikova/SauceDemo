package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.collections.Objects;

public class LoginTest extends BaseTest {

    @Test(testName = "Логин с корректными кредами",
            description = "Логин с корректными логином и паролем"
    )
    public void checkLoginWithCorrectCred() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        Assert.assertEquals(productsPage.getTitle(), "Products");
    }

    @Test(testName = "Логин с пустым паролем",
            description = "Логин с корректным логином и пустым паролем",
            enabled = false)
    public void checkLoginWithEmptyPassword() {
        loginPage.open();
        loginPage.login("standard_user", "");
        Assert.assertEquals(loginPage.getErrorMessage(), "Epic sadface: Password is required");
    }

    @Test(testName = "Логин с пустым логином",
            description = "Логин с пустым логином и корректным паролем",
            enabled = false)
    public void checkLoginWithEmptyUserName() {
        loginPage.open();
        loginPage.login("", "secret_sauce");
        Assert.assertEquals(loginPage.getErrorMessage(), "Epic sadface: Username is required");
    }

    @Test(testName = "Логин с некорректными паролем",
            description = "Логин с некорректным логином и некорректным паролем",
            enabled = false)
    public void checkLoginWithNegativeCred() {
        loginPage.open();
        loginPage.login("test", "test");
        Assert.assertEquals(loginPage.getErrorMessage(), "Epic sadface: Username and password do not" +
                " match any user in this service");
    }

    @DataProvider(name = "Данные для негативного логина")
    public Object[][] loginData() {
        return new Object[][]{
                {"standard_user", "", "Epic sadface: Password is required"},
                {"", "secret_sauce", "Epic sadface: Username is required"},
                {"test", "rest", "Epic sadface: Username and password do not match any user in this service"}
        };
    }

    @Test(dataProvider = "Данные для негативного логина", testName = "Негативный логин",
            description = "Логин с пустыми и некорректными логином и паролем")
    public void negativeLogin(String user, String password, String errorMessage) {
        loginPage.open();
        loginPage.login(user, password);
        Assert.assertEquals(loginPage.getErrorMessage(), errorMessage);
    }
}
