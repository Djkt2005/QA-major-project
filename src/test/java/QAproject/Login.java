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
        driver.get("https://www.quikr.com/");

        
        Thread.sleep(3000);
        // Wait and click "Login" button
        WebElement loginBtn = driver.findElement(By.xpath("//label[contains(text(),'Login/Register')]"));

        loginBtn.click();
//        
        Thread.sleep(3000);
        WebElement email = driver.findElement(By.xpath("(//input[@type='text'])[3]"));
        email.sendKeys("krishanttanti@gmail.com");
        
        email = driver.findElement(By.xpath("//button[contains(text(),'Continue')]"));
        email.click();
        
        Thread.sleep(3000);
        WebElement pass = driver.findElement(By.xpath("//input[@type='password']"));
        pass.sendKeys("Chitra@1977");
        
        WebElement btn = driver.findElement(By.xpath("//button[contains(text(),'Login')]"));
        btn.click();
        
    }


    
//    @AfterMethod
//    public void closeBrowser() throws InterruptedException {
//        Thread.sleep(3000);
//        driver.quit();
//    }
}
