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

public class WebDriverSeleniumHQTest {

    private WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void browserSetup(){
        System.setProperty("webdriver.chrome.driver", "D://webDriver//chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://минидино.рф/");
    }

    @Test
    public void findByFullProductNameTest() throws InterruptedException {

        String inputName = "Шапка трикотажная ушки фламинго";

        WebElement searchIcon = waitForElementLocatorBy(driver, By.className("header-main-menu-search"));
        Thread.sleep(4000);
        searchIcon.click();

        //другие варианты локатора, которые по какой-то причине не работают
        // WebElement searchInput = waitForElementLocatorBy(driver, By.id("f_4dc1b22d-62e1-4372-9cc8-23bbf0246ea4")); //почему-то всегда разный
        // WebElement searchInput = waitForElementLocatorBy(driver, By.xpath("//input[@type='search']"));
        // WebElement searchInput = waitForElementLocatorBy(driver, By.cssSelector("form.search__form input[type='search']"));


        WebElement searchInput = waitForElementLocatorBy(driver, By.xpath("/html/body/div[1]/div/header/div[2]/div[5]/div/div[3]/form/label/div/div/div/input"));

        Thread.sleep(2000);
        searchInput.sendKeys(inputName, Keys.ENTER);

        WebElement nameOfProduct = waitForElementLocatorBy(driver, By.cssSelector("div.category-item-description__title"));

        String productNameOnPage = nameOfProduct.getText();

        Assert.assertEquals(productNameOnPage.toLowerCase(), inputName.toLowerCase(), "No such product");
    }

    @AfterTest(alwaysRun = true)
    public void closeBrowser(){
            driver.quit();
            driver = null;
    }



    private  static WebElement waitForElementLocatorBy (WebDriver driver, By by){
        return new WebDriverWait(driver, Duration.ofMillis(8000))
                .until(ExpectedConditions.presenceOfElementLocated(by));
    }

}

