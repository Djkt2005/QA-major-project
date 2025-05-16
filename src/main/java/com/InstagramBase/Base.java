package com.InstagramBase;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Base {
	public Properties prop;
	public WebDriver driver;
	public JavascriptExecutor js;
	
	public Base() {
		prop = new Properties();
		FileInputStream file;
		try {
			file = new FileInputStream("src/main/resources/config.properties");
			prop.load(file);
		} 
		catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}	
	}
	public void initialize() {
		String browserName = prop.getProperty("browser");
		if("chrome".equals(browserName)){
			
			WebDriverManager.chromedriver().setup();
	        driver = new ChromeDriver();
	        driver.manage().window().maximize();
	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 
	        js = (JavascriptExecutor)driver;
			System.out.println("chrome driver initialized");
		}
	}
}
