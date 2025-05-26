package com.QAproject.loginTest;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.ITestContext;

import com.Instagram.base.Base;
import com.QAproject.login.Login;

public class LoginTest extends Base {
	Login login;

	@BeforeMethod
	public void setUp(ITestContext context) {
		initialize(); 
		context.setAttribute("driver", driver);
		driver.get("https://www.instagram.com/");
		login = new Login(driver);
	}
	
	@Test
	public void testTitle() {
		String expectedTitle = "Instagram";
		String actualTitle = login.getTitle();
		Assert.assertEquals(actualTitle, expectedTitle, "Title mismatch");
	}
	
	@Test 
	public void testLogin() throws InterruptedException {
		Assert.assertTrue(login.didLogin(), "Login failed");
	}

	@Test
	public void testInvalidUsername() throws InterruptedException {
		Assert.assertFalse(login.loginWithCredentials("invalid_username", "Ronit@2005"), 
			"Login should fail with invalid username");
	}

	@Test
	public void testInvalidPassword() throws InterruptedException {
		Assert.assertFalse(login.loginWithCredentials("ktforqa@gmail.com", "wrong_password"), 
			"Login should fail with invalid password");
	}

	@Test
	public void testEmptyCredentials() throws InterruptedException {
		Assert.assertFalse(login.loginWithCredentials("", ""), 
			"Login should fail with empty credentials");
	}

	@Test
	public void testSpecialCharactersUsername() throws InterruptedException {
		Assert.assertFalse(login.loginWithCredentials("test@#$%^&*()", "Ronit@2005"), 
			"Login should fail with special characters in username");
	}

	@Test
	public void testLongPassword() throws InterruptedException {
		Assert.assertFalse(login.loginWithCredentials("ktforqa@gmail.com", "a".repeat(100)), 
			"Login should fail with very long password");
	}
	
	@AfterMethod(alwaysRun = true)
	public void closeBrowser() throws InterruptedException {
		if (driver != null) {
			driver.quit();
		} else {
			System.out.println("Driver was null, browser not closed.");
		}
	}
}