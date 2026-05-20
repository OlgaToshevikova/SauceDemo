package tests;

import io.qameta.allure.*;
import lombok.extern.log4j.Log4j2;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.collections.Objects;

@Log4j2
public class LoginTest extends BaseTest {

    @Test(testName = "Логин с корректными кредами",
            description = "Логин с корректными логином и паролем"
    )
    @Description("Логин с корректными логином и паролем")
    @Epic("E2E")
    @Feature("checkLoginWithCorrectCred")
    @Story("Positive Login")
    @Severity(SeverityLevel.CRITICAL)
    @Link("https://github.com/allure-framework/allure-maven")
    @TmsLink("NGR-141")
    @Owner("Я")
    public void checkLoginWithCorrectCred() {
        log.info("Login with correct creds user '\"standard_user\"' and password '\"secret_sauce\"'");
        loginPage.open()
                .isPageOpened();
        loginPage.login("standard_user", "secret_sauce");
        Assert.assertEquals(productsPage.getTitle(), "Products");
    }

    @Test(testName = "Логин с пустым паролем",
            description = "Логин с корректным логином и пустым паролем",
            enabled = false)
    @Description("Логин с корректным логином и пустым паролем")
    @Epic("E2E")
    @Feature("checkLoginWithEmptyPassword")
    @Story("Negative Login")
    @Severity(SeverityLevel.CRITICAL)
    @Link("https://github.com/allure-framework/allure-maven")
    @TmsLink("NGR-142")
    @Owner("Я")
    public void checkLoginWithEmptyPassword() {
        log.info("Login with empty password");
        loginPage.open();
        loginPage.login("standard_user", "");
        Assert.assertEquals(loginPage.getErrorMessage(), "Epic sadface: Password is required");
    }

    @Test(testName = "Логин с пустым логином",
            description = "Логин с пустым логином и корректным паролем",
            enabled = false)

    public void checkLoginWithEmptyUserName() {
        log.info("Login with empty name");
        loginPage.open();
        loginPage.login("", "secret_sauce");
        Assert.assertEquals(loginPage.getErrorMessage(), "Epic sadface: Username is required");
    }

    @Test(testName = "Логин с некорректными паролем",
            description = "Логин с некорректным логином и некорректным паролем",
            enabled = false)
    public void checkLoginWithNegativeCred() {
        log.info("Login with incorrect creds");
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

    @Description("Логин с пустыми и некорректными логином и паролем")
    @Epic("E2E")
    @Feature("negativeLogin")
    @Story("Negative Login")
    @Severity(SeverityLevel.CRITICAL)
    @Link("https://github.com/allure-framework/allure-maven")
    @TmsLink("NGR-144")
    @Owner("Я")
    @Test(dataProvider = "Данные для негативного логина", testName = "Негативный логин",
            description = "Логин с пустыми и некорректными логином и паролем")
    public void negativeLogin(String user, String password, String errorMessage) {
        loginPage.open();
        loginPage.login(user, password);
        Assert.assertEquals(loginPage.getErrorMessage(), errorMessage);
    }
}
