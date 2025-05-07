package QAproject;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class switchto {
	WebDriver driver;
    WebDriverWait wait;
    String id;
    JavascriptExecutor js;
    WebElement element;

    @BeforeMethod
    public void openBrowser() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        js = (JavascriptExecutor)driver;
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
        Thread.sleep(2000);
    }
    @Test
    public void test1() throws InterruptedException {
    	String xpath = "(//div[@class=\"x1n2onr6 x6s0dn4 x78zum5\"])[13]";
    	element = driver.findElement(By.xpath(xpath));
    	element.click();
    	Set<String> windows =driver.getWindowHandles();
		Iterator<String> tab = windows.iterator();
		String parent = tab.next();
		String child = tab.next();
		driver.switchTo().window(child);
    	String title = (String)js.executeScript("return document.title;");
    	
    	driver.close();
		driver.switchTo().window(parent);
		
		js.executeScript("console.log(arguments[0])",title);
		
		Thread.sleep(3000);
    }
    @AfterMethod 
    public void closeBrowser() throws InterruptedException {
        Thread.sleep(3000);
        driver.quit();
    }
}
