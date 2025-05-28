package com.Instagram.window;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.Instagram.base.Base;

public class WindowHandler extends Base {
    WebDriver driver;
    WebDriverWait wait;
    JavascriptExecutor js;

    public WindowHandler(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.js = (JavascriptExecutor) driver;
    }

    public boolean clickAndSwitchToNewWindow(String xpath) {
        try {
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
            element.click();
            
            Set<String> windows = driver.getWindowHandles();
            Iterator<String> iterator = windows.iterator();
            String parentWindow = iterator.next();
            String childWindow = iterator.next();
            
            driver.switchTo().window(childWindow);
            return true;
        } catch (Exception e) {
            System.out.println("Error switching to new window: " + e.getMessage());
            return false;
        }
    }

    public boolean switchToParentWindow() {
        try {
            Set<String> windows = driver.getWindowHandles();
            Iterator<String> iterator = windows.iterator();
            String parentWindow = iterator.next();
            driver.switchTo().window(parentWindow);
            return true;
        } catch (Exception e) {
            System.out.println("Error switching to parent window: " + e.getMessage());
            return false;
        }
    }

    public boolean closeCurrentWindow() {
        try {
            driver.close();
            return true;
        } catch (Exception e) {
            System.out.println("Error closing current window: " + e.getMessage());
            return false;
        }
    }

    public String getCurrentWindowTitle() {
        try {
            return (String) js.executeScript("return document.title;");
        } catch (Exception e) {
            System.out.println("Error getting window title: " + e.getMessage());
            return null;
        }
    }

    public boolean clickAndSwitchToNewTab(String xpath) {
        try {
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
            element.click();
            
            Set<String> tabs = driver.getWindowHandles();
            Iterator<String> iterator = tabs.iterator();
            String parentTab = iterator.next();
            String childTab = iterator.next();
            
            driver.switchTo().window(childTab);
            return true;
        } catch (Exception e) {
            System.out.println("Error switching to new tab: " + e.getMessage());
            return false;
        }
    }

    public int getWindowCount() {
        try {
            return driver.getWindowHandles().size();
        } catch (Exception e) {
            System.out.println("Error getting window count: " + e.getMessage());
            return 0;
        }
    }
} 