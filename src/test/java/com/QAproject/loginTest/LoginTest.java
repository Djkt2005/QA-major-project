package com.QAproject.loginTest;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.InstagramBase.Base;
import com.QAproject.login.Login;



public class LoginTest extends Base{
	Login login;
	@BeforeMethod
	public void setUp() {
	    login = new Login();
	}
	
	@Test
	public void testTilte() {
		String title = "Instagramkt";
		Assert.assertEquals(login.getTitle(), title);
	}
	@Test 
	public void testLogin() throws InterruptedException{
		Assert.assertTrue(login.didLogin());
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