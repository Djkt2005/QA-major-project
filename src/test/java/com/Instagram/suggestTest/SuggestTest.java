package com.Instagram.suggestTest;

import java.util.List;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.ITestContext;

import com.Instagram.base.Base;
import com.Instagram.suggest.Suggest;
import com.QAproject.login.Login;

public class SuggestTest extends Base {
    private Suggest suggest;
    private Login login;

    @BeforeMethod
    public void setUp(ITestContext context) {
        initialize();
        context.setAttribute("driver", driver);
        driver.get("https://www.instagram.com/");
        
        // Login first
        login = new Login(driver);
        login.didLogin();
        
        // Initialize suggest
        suggest = new Suggest(driver);
    }

    @Test
    public void testNavigateToExplore() {
        Assert.assertTrue(suggest.navigateToExplore(), "Failed to navigate to explore page");
    }

    @Test
    public void testGetSuggestedAccounts() {
        suggest.navigateToExplore();
        List<String> accounts = suggest.getSuggestedAccounts();
        Assert.assertFalse(accounts.isEmpty(), "No suggested accounts found");
        System.out.println("Found " + accounts.size() + " suggested accounts");
    }

    @Test
    public void testFollowAndUnfollowSuggestedAccount() {
        suggest.navigateToExplore();
        List<String> accounts = suggest.getSuggestedAccounts();
        
        if (!accounts.isEmpty()) {
            String accountToFollow = accounts.get(0);
            // Follow account
            Assert.assertTrue(suggest.followSuggestedAccount(accountToFollow), 
                "Failed to follow account: " + accountToFollow);
            
            // Unfollow account
            Assert.assertTrue(suggest.unfollowSuggestedAccount(accountToFollow), 
                "Failed to unfollow account: " + accountToFollow);
        } else {
            Assert.fail("No accounts available to test follow/unfollow");
        }
    }

    @Test
    public void testSearchAndFollow() {
        String searchTerm = "selenium"; // Example search term
        Assert.assertTrue(suggest.searchAndFollow(searchTerm), 
            "Failed to search and follow account with term: " + searchTerm);
    }

    @Test
    public void testFollowMultipleAccounts() {
        suggest.navigateToExplore();
        List<String> accounts = suggest.getSuggestedAccounts();
        
        if (accounts.size() >= 3) {
            // Follow first 3 accounts
            for (int i = 0; i < 3; i++) {
                Assert.assertTrue(suggest.followSuggestedAccount(accounts.get(i)), 
                    "Failed to follow account: " + accounts.get(i));
            }
            
            // Unfollow all followed accounts
            for (int i = 0; i < 3; i++) {
                Assert.assertTrue(suggest.unfollowSuggestedAccount(accounts.get(i)), 
                    "Failed to unfollow account: " + accounts.get(i));
            }
        } else {
            Assert.fail("Not enough accounts available to test multiple follow/unfollow");
        }
    }

    @Test
    public void testInvalidSearchTerm() {
        String invalidSearchTerm = "!@#$%^&*()"; // Invalid search term
        Assert.assertFalse(suggest.searchAndFollow(invalidSearchTerm), 
            "Search and follow should fail with invalid search term");
    }

    @AfterMethod(alwaysRun = true)
    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }
} 