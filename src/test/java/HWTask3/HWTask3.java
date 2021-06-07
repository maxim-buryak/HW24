package HWTask3;

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

import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.testng.Assert.assertTrue;

public class HWTask3 extends Task3 {

    WebDriver driver;
    WebDriverWait wait;
    WebElement element;
    String initialUrl = "http://demo.guru99.com/test/guru99home/";

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
    public void videoPlayer(){
        By playButton = By.xpath("//button[@class='ytp-large-play-button ytp-button']");
        By pauseButton = By.xpath("//button[@aria-label='Пауза (k)']");
        By playerFrame = By.xpath("(//iframe)[1]");

        element = driver.findElement(playerFrame);
        driver.switchTo().frame(element);
        wait.until(presenceOfElementLocated(playButton));
        driver.findElement(playButton).click();

        wait.until(presenceOfElementLocated(pauseButton));
        element = driver.findElement(pauseButton);
        mouseMove(driver, element);
        assertTrue(driver.findElement(pauseButton).isDisplayed(), "Pause button is visible");

        driver.switchTo().parentFrame();
        element = driver.findElement(By.xpath("(//div[@class='rt-grid-6 rt-omega'])[1]"));
        mouseMove(driver, element);

        element = driver.findElement(playerFrame);
        driver.switchTo().frame(element);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        assertTrue(driver.findElement(By.xpath("//button[@aria-label='Пауза (k)'][@title='Пауза (k)']")).isEnabled(), "Pause button is hidden");

    }
}
