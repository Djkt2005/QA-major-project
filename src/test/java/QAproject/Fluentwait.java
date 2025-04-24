package QAproject;

import java.time.Duration;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Fluentwait {
    WebDriver driver;
    FluentWait<WebDriver> fluentWait;

    @BeforeMethod
    public void openBrowser() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        fluentWait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofSeconds(2))
                .ignoring(NoSuchElementException.class);
    }

    @Test(priority = 1)
    public void olx() throws InterruptedException {
        driver.get("https://www.olx.in/en-in");

        WebElement inputbox = fluentWait.until(driver ->
                driver.findElement(By.xpath("//input[@data-aut-id='searchBox']")));
        inputbox.sendKeys("bat");

        WebElement searchbtn = fluentWait.until(driver ->
                driver.findElement(By.xpath("//div[@data-aut-id='btnSearch']")));
        searchbtn.click();

        WebElement firstprd = fluentWait.until(driver ->
                driver.findElement(By.xpath("//a[contains(@href, 'cricket-bat')]")));
        firstprd.click();
    }

    @AfterMethod
    public void closeBrowser() throws InterruptedException {
        Thread.sleep(3000);
        driver.quit();
    }
}

