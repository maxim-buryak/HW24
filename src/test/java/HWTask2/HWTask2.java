package HWTask2;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Set;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.testng.Assert.assertTrue;

public class HWTask2 {
    WebDriver driver;
    WebDriverWait wait;
    String correctLogin = "1303";
    String correctPassword = "Guru99";
    String initialUrl = "http://demo.guru99.com/Agile_Project/Agi_V1/index.php";

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
    public void resetCookies(){
        driver.findElement(By.name("uid")).sendKeys(correctLogin);
        driver.findElement(By.name("password")).sendKeys(correctPassword);
        driver.findElement(By.name("btnLogin")).click();
        WebElement element = wait.until(presenceOfElementLocated(By.xpath("//a[@href='Logout.php']")));
        assertTrue(element.getText().contains("Log out"), "Checking that user still logged in");


        Set<Cookie> cookies = driver.manage().getCookies();
        cookies.forEach(System.out::println);

        driver.manage().deleteAllCookies();
        driver.navigate().refresh();

        WebElement element1 = wait.until(presenceOfElementLocated(By.xpath("//a[@href='Logout.php']")));
        assertTrue(element1.getText().contains("Log out"), "Checking that user still logged in");
    }
}
