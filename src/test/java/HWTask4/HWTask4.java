package HWTask4;

import com.sun.org.apache.xpath.internal.res.XPATHErrorResources_en;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class HWTask4 {
    WebDriver driver;
    WebDriverWait wait;
    WebElement element;
    String initialUrl = "http://demo.guru99.com/test/drag_drop.html";

    @BeforeClass
    public void setupBrowser() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
        driver.manage().window().maximize();
    }

    @AfterClass
    public void closeBworser() {
        driver.quit();
    }

    @BeforeMethod
    public void navigateAction() {
        driver.get(initialUrl);
    }
    @Test
    public void dragAndDrop(){
        wait.until(presenceOfElementLocated(By.tagName("h2")));
        element = driver.findElement(By.tagName("h2"));
        scrollAction(driver, element);
        WebElement origin = driver.findElement(By.cssSelector("#fourth:nth-child(2)"));
        WebElement destination = driver.findElement(By.id("amt7"));
        dragAndDropAction(driver, origin, destination);
        assertTrue(driver.findElement(By.xpath("//ol[@id='amt7']/li[@data-id='2']")).isDisplayed());
        WebElement originSecond = driver.findElement(By.cssSelector("#fourth:nth-child(4)"));
        WebElement destinationSecond = driver.findElement(By.id("amt8"));
        dragAndDropAction(driver, originSecond, destinationSecond);
        assertTrue(driver.findElement(By.xpath("//ol[@id='amt8']/li[@data-id='2']")).isDisplayed());
        WebElement originBank = driver.findElement(By.id("credit2"));
        WebElement destinationBank = driver.findElement(By.id("bank"));
        dragAndDropAction(driver, originBank, destinationBank);
        assertTrue(driver.findElement(By.xpath("//ol[@id='bank']/li[@data-id='5']")).isDisplayed());
        WebElement originSales = driver.findElement(By.id("credit1"));
        WebElement destinationSales = driver.findElement(By.id("loan"));
        dragAndDropAction(driver, originSales, destinationSales);
        assertTrue(driver.findElement(By.xpath("//ol[@id='loan']/li[@data-id='6']")).isDisplayed());
        assertEquals(driver.findElement(By.xpath("(//a[@class='button button-green'])[1]")).getText(), "Perfect!", "Checking if Perfect!");
    }
}
