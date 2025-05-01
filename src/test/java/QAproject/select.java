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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class select {
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
	    
	    @Test(priority = 1)
	    public void dropdowns() throws InterruptedException {
	    	Thread.sleep(5000);
	        WebElement element = driver.findElement(By.xpath("//select[@class=\"x1ypdohk x5yr21d x17qophe xg01cxk x10l6tqk x13vifvy xh8yej3\"]"));
	        Select dropdown = new Select(element);
	        dropdown.selectByValue("hi");
	        Thread.sleep(2000);
	        
	    }
	    @AfterMethod 
	    public void closeBrowser() throws InterruptedException {
	        Thread.sleep(3000);
	        driver.quit();
	    }
}
