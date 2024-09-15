import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class AddToCart {
    WebDriver driver;
    Actions actions;
    WebDriverWait wait;

    @BeforeMethod

    public void browserIntialization() {


        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.get("https://www.costco.com/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().timeouts();
    }

@Test

public void navigateToBreakfastPage() {

        actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.xpath("//a[@aria-label='Grocery'][contains(@class,'root MuiButton')]"))).perform();
        driver.findElement(By.linkText("Breakfast")).click();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[contains(text(),'Filter Results')]")));

        driver.findElement(By.xpath("//div[contains(text(),'Breakfast & Snack Bars')]")).click();
        driver.findElement(By.linkText("Nature's Bakery Fig Bar, Variety Pack, 2 oz, 40-count")).click();
        driver.findElement(By.xpath("//input[@id='add-to-cart-btn']")).click();
        driver.findElement(By.xpath("//a[contains(text(),'View Cart')][contains(@class,'primary btn-block')]")).click();
        String Expected="Subtotal";
        String actualResults=driver.findElement(By.xpath("//h2[contains(text(),'Subtotal')]")).getText();
        Assert.assertTrue(actualResults.contains(Expected));

    }

@AfterMethod

public void browserCleanUp(){
        driver.quit();
    }
}
