package QAproject;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ExplicitWait {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeMethod
    public void openBrowser() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(30)); 
    }

    @Test(priority = 1)
    public void olx() throws InterruptedException {
        driver.get("https://www.olx.in/en-in");

        WebElement inputbox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@data-aut-id='searchBox']")));
        inputbox.sendKeys("bat");

        WebElement searchbtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@data-aut-id='btnSearch']")));
        searchbtn.click();

        WebElement firstprd = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@href, 'cricket-bat')]")));
        firstprd.click();
    }

    @AfterMethod
    public void closeBrowser() throws InterruptedException {
        Thread.sleep(3000);
        driver.quit();
    }
}
