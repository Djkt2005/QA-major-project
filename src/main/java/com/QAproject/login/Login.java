package com.QAproject.login;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.InstagramBase.Base;


public class Login extends Base{
	@FindBy(id="icp-nav-flyout")
	WebElement element;
	
	@FindBy(xpath="//a[@href=\"#switch-lang=en_IN\"]")
	WebElement lang;
	
	public Login() {
		this.initialize();
		driver.get("https://www.instagram.com/");
		
	}
	public String getTitle() {
		return driver.getTitle();
	}
	
	public boolean didLogin() {
	    try {
	    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        WebElement email = driver.findElement(By.xpath("(//input[@name=\"username\"])"));
	        email.sendKeys("ktforqa@gmail.com");

	        Thread.sleep(2000); 
	        WebElement pass = driver.findElement(By.xpath("//input[@name=\"password\"]"));
	        pass.sendKeys("Ronit@2005");

	        WebElement btn = driver.findElement(By.xpath("//button[@type=\"submit\"]"));
	        btn.click();

	        
	         
	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='x1qjc9v5 x9f619 x78zum5 xdt5ytf xln7xf2 xk390pu x1xmf6yo x1n2onr6 x1y2wqyl x11njtxf']")));

	        return true;

	    } catch (Exception e) {
	        System.out.println("Error during login: " + e.getMessage());
	        return false;
	    }
	}

}
