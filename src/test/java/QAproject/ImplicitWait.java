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
   		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(35));
   	}
    
    @Test (priority=1)
    public void olx() throws InterruptedException {
    	driver.get("https://www.olx.in/en-in");
		WebElement inputbox = driver.findElement(By.xpath("//input[@data-aut-id='searchBox']"));
		inputbox.sendKeys("bat");
		
		WebElement searchbtn = driver.findElement(By.xpath("//div[@data-aut-id=\"btnSearch\"]"));
		searchbtn.click();
		
		WebElement firstprd = driver.findElement(By.xpath("//a[@href=\"/en-in/item/sports-equipment-c100-cricket-bat-in-chittoor-pathanamthitta-iid-1794796394\"]"));
		firstprd.click();
    }
    
    @AfterMethod 
    public void closeBrowser() throws InterruptedException {
    	Thread.sleep(3000);
    	driver.quit();
    }
       
}
