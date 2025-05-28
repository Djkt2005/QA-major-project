package com.Instagram.pageview;

import java.time.Duration;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.Instagram.base.Base;

public class PageView extends Base {
    WebDriver driver;
    WebDriverWait wait;
    JavascriptExecutor js;

    public PageView(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.js = (JavascriptExecutor) driver;
    }

    public boolean scrollDown(int pixels) {
        try {
            js.executeScript("window.scrollBy(0, arguments[0]);", pixels);
            return true;
        } catch (Exception e) {
            System.out.println("Error scrolling down: " + e.getMessage());
            return false;
        }
    }

    public boolean scrollUp(int pixels) {
        try {
            js.executeScript("window.scrollBy(0, -arguments[0]);", pixels);
            return true;
        } catch (Exception e) {
            System.out.println("Error scrolling up: " + e.getMessage());
            return false;
        }
    }

    public boolean scrollToBottom() {
        try {
            js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
            return true;
        } catch (Exception e) {
            System.out.println("Error scrolling to bottom: " + e.getMessage());
            return false;
        }
    }

    public boolean scrollToTop() {
        try {
            js.executeScript("window.scrollTo(0, 0);");
            return true;
        } catch (Exception e) {
            System.out.println("Error scrolling to top: " + e.getMessage());
            return false;
        }
    }

    public boolean zoomIn(int percentage) {
        try {
            js.executeScript("document.body.style.zoom = arguments[0] + '%';", percentage);
            return true;
        } catch (Exception e) {
            System.out.println("Error zooming in: " + e.getMessage());
            return false;
        }
    }

    public boolean zoomOut(int percentage) {
        try {
            js.executeScript("document.body.style.zoom = arguments[0] + '%';", percentage);
            return true;
        } catch (Exception e) {
            System.out.println("Error zooming out: " + e.getMessage());
            return false;
        }
    }

    public boolean resetZoom() {
        try {
            js.executeScript("document.body.style.zoom = '100%';");
            return true;
        } catch (Exception e) {
            System.out.println("Error resetting zoom: " + e.getMessage());
            return false;
        }
    }

    public String getPageTitle() {
        try {
            return (String) js.executeScript("return document.title;");
        } catch (Exception e) {
            System.out.println("Error getting page title: " + e.getMessage());
            return null;
        }
    }

    public boolean scrollToElement(WebElement element) {
        try {
            js.executeScript("arguments[0].scrollIntoView(true);", element);
            return true;
        } catch (Exception e) {
            System.out.println("Error scrolling to element: " + e.getMessage());
            return false;
        }
    }
} 