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
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Login {
	WebDriver driver;
    WebDriverWait wait;

    @BeforeMethod
    public void openBrowser() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }
    
    @Test(priority = 1)
    public void login() throws InterruptedException {
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

    @AfterMethod
    public void closeBrowser() throws InterruptedException {
        Thread.sleep(3000);
        driver.quit();
    }
}
