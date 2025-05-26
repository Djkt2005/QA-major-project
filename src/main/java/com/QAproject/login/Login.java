package com.QAproject.login;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.Instagram.base.Base;

public class Login extends Base {
	WebDriver driver;

	public Login(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public String getTitle() {
		return driver.getTitle();
	}
	
	public boolean didLogin() {
		return loginWithCredentials("ktforqa@gmail.com", "Ronit@2005");
	}

	public boolean loginWithCredentials(String username, String password) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			WebElement email = driver.findElement(By.xpath("(//input[@name=\"username\"])"));
			email.sendKeys(username);

			Thread.sleep(2000); 
			WebElement pass = driver.findElement(By.xpath("//input[@name=\"password\"]"));
			pass.sendKeys(password);

			WebElement btn = driver.findElement(By.xpath("//button[@type=\"submit\"]"));
			btn.click();

			// Wait for captcha if it appears
			try {
				Thread.sleep(10000); // 10 second wait for captcha
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			// Check if login was successful by looking for the home feed
			try {
				wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("//div[@class='x1qjc9v5 x9f619 x78zum5 xdt5ytf xln7xf2 xk390pu x1xmf6yo x1n2onr6 x1y2wqyl x11njtxf']")));
				return true;
			} catch (Exception e) {
				// If we can't find the home feed element, login probably failed
				return false;
			}

		} catch (Exception e) {
			System.out.println("Error during login: " + e.getMessage());
			return false;
		}
	}
}
