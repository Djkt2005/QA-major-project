package QAproject;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class JSExecutor {
	WebDriver driver;
    WebDriverWait wait;
    String id;
    WebElement element;
    JavascriptExecutor js;

    @BeforeMethod
    public void openBrowser() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 
        js = (JavascriptExecutor)driver;
        driver.get("https://www.instagram.com/");
        
        Thread.sleep(3000);
       
        WebElement email = driver.findElement(By.xpath("(//input[@name=\"username\"])"));
        email.sendKeys("ktforqa@gmail.com");
        
        Thread.sleep(2000);
        WebElement pass = driver.findElement(By.xpath("//input[@name=\"password\"]"));
        pass.sendKeys("Ronit@2005");
        
        WebElement btn = driver.findElement(By.xpath("//button[@type=\"submit\"]"));
        btn.click();
        Thread.sleep(5000);
    }
    
    @Test(priority=1)
	public void exec() throws InterruptedException {
		
		js.executeScript("window.scrollBy(arguments[0],arguments[1]); console.log('scrolled')",0,10000);
		
		Thread.sleep(2000);
		js.executeScript("window.scrollBy(arguments[0],arguments[1]); console.log('scrolled')",0,-10000);
		js.executeScript("document.body.style.zoom = '40%';");
		Thread.sleep(2000);
		js.executeScript("document.body.style.zoom = '100%';");
		Thread.sleep(2000);
		js.executeScript("window.scrollBy(0,document.body.scrollHeight);");
		js.executeScript("window.scrollBy(0,-(document.body.scrollHeight));");
		Thread.sleep(2000);
		
		String s = (String)js.executeScript("return document.title;");
		
		System.out.println(s);
		
	}
    @AfterMethod 
    public void closeBrowser() throws InterruptedException {
        Thread.sleep(3000);
        driver.quit();
    }
    
    
}
