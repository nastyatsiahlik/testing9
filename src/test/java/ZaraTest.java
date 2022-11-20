import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import org.testng.Assert;


import java.time.Duration;

public class ZaraTest {

    private static WebDriver driver = new ChromeDriver();

    @BeforeTest
    public void browserSetup(){
        System.setProperty("webdriver.chrome.driver", "D:\\chromedriver\\chromedriver.exe");
        driver.get("https://www.zara.com/by/ru/");
    }

    @Test
    public void findByFullProductNameTest()  {


        String inputName = "Платье из бархата с вышивкой";

        WebElement searchIcon = waitForElementLocatorBy(driver, By.xpath("//span[text()=\"Поиск\"]"));
        searchIcon.click();

        WebElement searchInput = waitForElementLocatorBy(driver, By.xpath("//input[@id=\"search-products-form-combo-input\"]"));
        searchInput.click();
        searchInput.sendKeys(inputName, Keys.ENTER);


        WebElement productLink = waitForElementLocatorBy(driver, By.cssSelector("a.product-link"));
        productLink.click();
        WebElement nameOfProduct = waitForElementLocatorBy(driver, By.cssSelector("h1.product-detail-info__header-name"));
        String productNameOnPage = nameOfProduct.getText();

        Assert.assertEquals(productNameOnPage.toLowerCase(), inputName.toLowerCase(), "No such product");
    }

    @AfterTest
    public void closeBrowser(){
        driver.quit();

    }

    private  static WebElement waitForElementLocatorBy (WebDriver driver, By by){
        return new WebDriverWait(driver, Duration.ofMillis(4000))
                .until(ExpectedConditions.presenceOfElementLocated(by));
    }

}

