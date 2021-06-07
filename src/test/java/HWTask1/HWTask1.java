package HWTask1;

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

import java.awt.*;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.testng.Assert.assertEquals;
public class HWTask1 extends MethodforHWTask1 {
    WebDriver driver;
    WebDriverWait wait;
    String initialURL= "https://login.yahoo.com";
    Robot robot;
    WebElement element;

    @BeforeClass
    public void setupBrowser() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 20);
        driver.manage().window().maximize();
    }

    @AfterClass
    public void closeBworser() {
        driver.quit();
    }

    @BeforeMethod
    public void navigateAction() {
        driver.get(initialURL);
    }

    @Test
    public void TestGmail(){
        By username = By.cssSelector("input[name='username']");
        By password = By.id("login-passwd");
        By SigIn = By.id("ybarMailLink");
        By ImageButton = By.name("attach-from-computer");

        wait.until(presenceOfElementLocated(username));
        element = driver.findElement(username);
        element.click();

        driver.findElement(By.id("login-username")).sendKeys("testingBuryak@yahoo.com");
        driver.findElement(By.cssSelector("input[name='signin']")).click();

        wait.until(presenceOfElementLocated(password));
        driver.findElement(password).sendKeys("TTee1234");
        driver.findElement(By.id("login-signin")).click();

        wait.until(presenceOfElementLocated(SigIn));
        driver.findElement(SigIn).click();

        wait.until(presenceOfElementLocated(By.cssSelector("input[role='combobox']")));
        driver.findElement(By.cssSelector("a[data-test-id='compose-button']")).click();

        element = driver.findElement(By.id("message-to-field"));
        element.click();
        element.sendKeys("testingBuryak@yahoo.com");

        element =driver.findElement(By.cssSelector("input[data-test-id='compose-subject']"));
        element.click();
        element.sendKeys("Test");

        element = driver.findElement(By.xpath("(//div[@id='editor-container']/div)[1]"));
        element.click();
        element.sendKeys("Test");

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.cssSelector("button[data-test-id='icon-btn-attach']")).click();
        driver.findElement(ImageButton).click();

        attachImageRobot(robot);
        driver.findElement(By.xpath("//button[@data-test-id='compose-send-button']/span")).click();

        unexpectedAlert(wait, driver);
        try {
            Thread.sleep(200000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        driver.navigate().refresh();
        wait.until(presenceOfElementLocated(By.cssSelector("input[role='combobox']")));
        assertEquals(driver.findElement(By.cssSelector("span[title='Test email']")).getText(), "Test", "Checking that email");
        driver.findElement(By.xpath("//div[@data-test-id='attachment-icon-wrapper'])[1]")).click();

        element = driver.findElement(By.cssSelector("span[data-test-id='message-group-subject-text']"));
        assertEquals(element.getText(), "Test","Verifying email subject");

        element = driver.findElement(By.xpath("//div[@data-test-id='message-view-body-content']/div/div/div/div/div/div"));

        element = driver.findElement(By.cssSelector("span[title='data.txt']>div>span>span"));
        assertEquals(element.getText(), "data", "Verifying file is attached");

    }
}
