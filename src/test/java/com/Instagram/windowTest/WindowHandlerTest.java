package com.Instagram.windowTest;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.ITestContext;

import com.Instagram.base.Base;
import com.Instagram.window.WindowHandler;
import com.Instagram.login.Login;

public class WindowHandlerTest extends Base {
    private WindowHandler windowHandler;
    private Login login;

    @BeforeMethod
    public void setUp(ITestContext context) {
        initialize();
        context.setAttribute("driver", driver);
        driver.get("https://www.instagram.com/");
        
        // Login first
        login = new Login(driver);
        login.didLogin();
        
        // Initialize window handler
        windowHandler = new WindowHandler(driver);
    }

    @Test
    public void testClickAndSwitchToNewWindow() {
        String xpath = "(//div[@class=\"x1n2onr6 x6s0dn4 x78zum5\"])[13]";
        Assert.assertTrue(windowHandler.clickAndSwitchToNewWindow(xpath), 
            "Failed to click and switch to new window");
        Assert.assertNotNull(windowHandler.getCurrentWindowTitle(), 
            "Window title should not be null");
    }

    @Test
    public void testSwitchToParentWindow() {
        // First open a new window
        String xpath = "(//div[@class=\"x1n2onr6 x6s0dn4 x78zum5\"])[13]";
        windowHandler.clickAndSwitchToNewWindow(xpath);
        
        // Then switch back to parent
        Assert.assertTrue(windowHandler.switchToParentWindow(), 
            "Failed to switch to parent window");
    }

    @Test
    public void testCloseCurrentWindow() {
        // First open a new window
        String xpath = "(//div[@class=\"x1n2onr6 x6s0dn4 x78zum5\"])[13]";
        windowHandler.clickAndSwitchToNewWindow(xpath);
        
        // Then close it
        Assert.assertTrue(windowHandler.closeCurrentWindow(), 
            "Failed to close current window");
        
        // Switch back to parent
        Assert.assertTrue(windowHandler.switchToParentWindow(), 
            "Failed to switch to parent window");
    }

    @Test
    public void testGetCurrentWindowTitle() {
        String xpath = "(//div[@class=\"x1n2onr6 x6s0dn4 x78zum5\"])[13]";
        windowHandler.clickAndSwitchToNewWindow(xpath);
        String title = windowHandler.getCurrentWindowTitle();
        Assert.assertNotNull(title, "Window title should not be null");
    }

    @Test
    public void testClickAndSwitchToNewTab() {
        String xpath = "(//div[@class=\"x1n2onr6 x6s0dn4 x78zum5\"])[13]";
        Assert.assertTrue(windowHandler.clickAndSwitchToNewTab(xpath), 
            "Failed to click and switch to new tab");
        Assert.assertNotNull(windowHandler.getCurrentWindowTitle(), 
            "Tab title should not be null");
    }

    @Test
    public void testGetWindowCount() {
        // Should start with 1 window
        Assert.assertEquals(windowHandler.getWindowCount(), 1, 
            "Should start with 1 window");
        
        // Open new window
        String xpath = "(//div[@class=\"x1n2onr6 x6s0dn4 x78zum5\"])[13]";
        windowHandler.clickAndSwitchToNewWindow(xpath);
        
        // Should now have 2 windows
        Assert.assertEquals(windowHandler.getWindowCount(), 2, 
            "Should have 2 windows after opening new window");
    }

    @Test
    public void testWindowSequence() {
        // Test a sequence of window operations
        String xpath = "(//div[@class=\"x1n2onr6 x6s0dn4 x78zum5\"])[13]";
        
        // Open new window
        Assert.assertTrue(windowHandler.clickAndSwitchToNewWindow(xpath), 
            "Failed to open new window");
        
        // Get title
        String title = windowHandler.getCurrentWindowTitle();
        Assert.assertNotNull(title, "Window title should not be null");
        
        // Close window
        Assert.assertTrue(windowHandler.closeCurrentWindow(), 
            "Failed to close window");
        
        // Switch back to parent
        Assert.assertTrue(windowHandler.switchToParentWindow(), 
            "Failed to switch to parent window");
    }

    @AfterMethod(alwaysRun = true)
    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }
} 