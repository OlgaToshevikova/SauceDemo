import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;
/*
3. Создать новый Java-класс, в нем для ресурса
https://www.saucedemo.com/ составить список локаторов, можно искать на ВСЕХ страницах приложения (driver.fi ndElement(<локатор>)) для КАЖДОГО из примеров локаторов ниже:
• id
• name
• classname
• tagname
 • linktext
 • partiallinktext

 • xpath:
- поиск по атрибуту, например By.xpath("//tag[@attribute='value']");
- поиск по тексту, например By.xpath("//tag[text()='text']");
- поиск по частичному совпадению атрибута, например By.xpath("//tag[contains(@attribute,'text')]");
- поиск по частичному совпадению текста, например
By.xpath("//tag[contains(text(),'text')]");
- ancestor, например //*[text()='Enterprise Testing']//ancestor::div
- descendant
- following
- parent
- preceding
- Подсказка: XPath Axes
- *поиск элемента с условием AND, например
//input[@class='_2zrpKA_1dBPDZ' and @type='text']



 */


import java.time.Duration;
import java.util.HashMap;

public class LocatorsTest {
    @Test
    public void CheckLocators(){
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
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.name("password")).sendKeys("secret_sauce");
driver.findElement(By.className("submit-button")).click();
driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
driver.findElement(By.className("header_container"));
driver.findElement(By.tagName("select"));
driver.findElement(By.linkText("Twitter"));
driver.findElement(By.partialLinkText("Linked"));
//XPath
        driver.findElement(By.xpath("//img[@alt='Sauce Labs Backpack']"));
        driver.findElement(By.xpath("//a [text()='Twitter']"));
       driver.findElement(By.xpath("//img[contains(@alt,'Backpack')]"));
        driver.findElement(By.xpath("//a [contains(text(),'Twitt')]"));
        driver.findElement(By.xpath("//img[contains(@alt,'Backpack')]//ancestor::div"));
        driver.findElement(By.xpath("//a[@id='item_4_img_link']//descendant::img"));
        driver.findElement(By.xpath("//a[@id='item_4_img_link']//following::img"));
        driver.findElement(By.xpath("//a[@id='item_4_img_link']//parent::div"));
        driver.findElement(By.xpath("//a[@id='item_4_img_link']//preceding::div"));
        driver.findElement(By.xpath("//img[@alt='Sauce Labs Backpack' and @class='inventory_item_img']"));
    }
}
