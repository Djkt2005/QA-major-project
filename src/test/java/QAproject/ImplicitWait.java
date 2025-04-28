package QAproject;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;


public class ImplicitWait {
    WebDriver driver;
       
    @BeforeMethod
   	public void openBrowser() throws InterruptedException {
   		WebDriverManager.chromedriver().setup();
   		driver = new ChromeDriver();
   		driver.manage().window().maximize();
   		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
   	}
    
    @Test (priority=1)
    public void implicitwait() throws InterruptedException {
    	driver.get("https://www.quikr.com/");
    	Thread.sleep(3000);
		WebElement inputbox = driver.findElement(By.xpath("//input[@id=\"query\"]"));
		inputbox.sendKeys("car");
		inputbox.click();
		inputbox.sendKeys(Keys.ENTER);
		Thread.sleep(5000);
		WebElement firstprd = driver.findElement(By.xpath("(//div[@class=\"mdc-layout-grid__cell mdc-layout-grid__cell--span-4-desktop mdc-layout-grid__cell mdc-layout-grid__cell--span-12-phone\"])[1]"));
		firstprd.click();
    }
    
    @AfterMethod 
    public void closeBrowser() throws InterruptedException {
    	Thread.sleep(3000);
    	driver.quit();
    }
       
}
