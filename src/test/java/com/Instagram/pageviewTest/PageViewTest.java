package com.Instagram.pageviewTest;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.ITestContext;

import com.Instagram.base.Base;
import com.Instagram.pageview.PageView;
import com.Instagram.login.Login;

public class PageViewTest extends Base {
    private PageView pageView;
    private Login login;

    @BeforeMethod
    public void setUp(ITestContext context) {
        initialize();
        context.setAttribute("driver", driver);
        driver.get("https://www.instagram.com/");
        
        // Login first
        login = new Login(driver);
        login.didLogin();
        
        // Initialize pageView
        pageView = new PageView(driver);
    }

    @Test
    public void testScrollDown() {
        Assert.assertTrue(pageView.scrollDown(1000), "Failed to scroll down");
    }

    @Test
    public void testScrollUp() {
        // First scroll down
        pageView.scrollDown(1000);
        // Then scroll up
        Assert.assertTrue(pageView.scrollUp(500), "Failed to scroll up");
    }

    @Test
    public void testScrollToBottom() {
        Assert.assertTrue(pageView.scrollToBottom(), "Failed to scroll to bottom");
    }

    @Test
    public void testScrollToTop() {
        // First scroll to bottom
        pageView.scrollToBottom();
        // Then scroll to top
        Assert.assertTrue(pageView.scrollToTop(), "Failed to scroll to top");
    }

    @Test
    public void testZoomIn() {
        Assert.assertTrue(pageView.zoomIn(150), "Failed to zoom in");
    }

    @Test
    public void testZoomOut() {
        Assert.assertTrue(pageView.zoomOut(50), "Failed to zoom out");
    }

    @Test
    public void testResetZoom() {
        // First zoom in
        pageView.zoomIn(150);
        // Then reset zoom
        Assert.assertTrue(pageView.resetZoom(), "Failed to reset zoom");
    }

    @Test
    public void testGetPageTitle() {
        String title = pageView.getPageTitle();
        Assert.assertNotNull(title, "Page title should not be null");
        Assert.assertTrue(title.contains("Instagram"), "Page title should contain 'Instagram'");
    }

    @Test
    public void testScrollToElement() {
        try {
            // Find a post element to scroll to
            WebElement post = driver.findElement(By.xpath("//article[1]"));
            Assert.assertTrue(pageView.scrollToElement(post), "Failed to scroll to element");
        } catch (Exception e) {
            Assert.fail("Could not find element to scroll to: " + e.getMessage());
        }
    }

    @Test
    public void testScrollSequence() {
        // Test a sequence of scroll operations
        Assert.assertTrue(pageView.scrollDown(500), "Failed to scroll down first time");
        Assert.assertTrue(pageView.scrollDown(500), "Failed to scroll down second time");
        Assert.assertTrue(pageView.scrollUp(300), "Failed to scroll up");
        Assert.assertTrue(pageView.scrollToBottom(), "Failed to scroll to bottom");
        Assert.assertTrue(pageView.scrollToTop(), "Failed to scroll to top");
    }

    @Test
    public void testZoomSequence() {
        // Test a sequence of zoom operations
        Assert.assertTrue(pageView.zoomIn(150), "Failed to zoom in");
        Assert.assertTrue(pageView.zoomIn(200), "Failed to zoom in more");
        Assert.assertTrue(pageView.zoomOut(100), "Failed to zoom out");
        Assert.assertTrue(pageView.resetZoom(), "Failed to reset zoom");
    }

    @AfterMethod(alwaysRun = true)
    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }
}