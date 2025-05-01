package QAproject;

import java.time.Duration;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class wait {
    WebDriver driver;
       
    @BeforeMethod
    public void openBrowser() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 
        driver.get("https://www.instagram.com/");
        
        Thread.sleep(3000);
       
        WebElement email = driver.findElement(By.xpath("(//input[@name=\"username\"])"));
        email.sendKeys("ktforqa@gmail.com");
        
        Thread.sleep(2000);
        WebElement pass = driver.findElement(By.xpath("//input[@name=\"password\"]"));
        pass.sendKeys("Ronit@2005");
        
        WebElement btn = driver.findElement(By.xpath("//button[@type=\"submit\"]"));
        btn.click();
    }
    
    @Test(priority=1)
    public void implicitwait() throws InterruptedException {
        Thread.sleep(3000);
        WebElement Searchbtn = driver.findElement(By.xpath("//span[text()='Search']"));
        Searchbtn.click();
        
        WebElement inputbox = driver.findElement(By.xpath("//input[@placeholder=\"Search\"]"));
        inputbox.sendKeys("Ed Sheeran");
        
        Thread.sleep(5000);
        WebElement firstprd = driver.findElement(By.xpath("//div[@class=\"x9f619 x78zum5 xdt5ytf x1iyjqo2 x6ikm8r x1odjw0f xh8yej3 xocp1fn\"]/a[1]"));
        firstprd.click();
    }

    @Test(priority=2)
    public void explicitWaitTest() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement searchBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Search']")));
        searchBtn.click();

        WebElement inputBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Search']")));
        inputBox.sendKeys("Ed Sheeran");

        WebElement firstPrd = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='x9f619 x78zum5 xdt5ytf x1iyjqo2 x6ikm8r x1odjw0f xh8yej3 xocp1fn']/a[1]")));
        firstPrd.click();
    }

    
    @Test(priority=3)
    public void fluentWaitTest() {
        FluentWait<WebDriver> fluentWait = new FluentWait<>(driver)
            .withTimeout(Duration.ofSeconds(15))
            .pollingEvery(Duration.ofSeconds(2))
            .ignoring(NoSuchElementException.class);

        WebElement searchBtn = fluentWait.until(driver -> driver.findElement(By.xpath("//span[text()='Search']")));
        searchBtn.click();

        WebElement inputBox = fluentWait.until(driver -> driver.findElement(By.xpath("//input[@placeholder='Search']")));
        inputBox.sendKeys("Ed Sheeran");

        WebElement firstPrd = fluentWait.until(driver -> driver.findElement(By.xpath("//div[@class='x9f619 x78zum5 xdt5ytf x1iyjqo2 x6ikm8r x1odjw0f xh8yej3 xocp1fn']/a[1]")));
        firstPrd.click();
    }

    
    @AfterMethod 
    public void closeBrowser() throws InterruptedException {
        Thread.sleep(3000);
        driver.quit();
    }
}
