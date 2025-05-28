package com.Instagram.searchTest;

import java.util.List;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.ITestContext;

import com.Instagram.base.Base;
import com.Instagram.search.Search;
import com.Instagram.login.Login;

public class SearchTest extends Base {
    private Search search;
    private Login login;

    @BeforeMethod
    public void setUp(ITestContext context) {
        initialize();
        context.setAttribute("driver", driver);
        driver.get("https://www.instagram.com/");
        
        // Login first
        login = new Login(driver);
        login.didLogin();
        
        // Initialize search
        search = new Search(driver);
    }

    @Test
    public void testBasicSearch() {
        Assert.assertTrue(search.clickSearchButton(), "Failed to click search button");
        Assert.assertTrue(search.searchForUser("selenium"), "Failed to search for user");
        Assert.assertTrue(search.isSearchResultsVisible(), "Search results not visible");
    }

    @Test
    public void testSearchAndClickFirstResult() {
        Assert.assertTrue(search.clickSearchButton(), "Failed to click search button");
        Assert.assertTrue(search.searchForUser("selenium"), "Failed to search for user");
        Assert.assertTrue(search.clickFirstSearchResult(), "Failed to click first search result");
    }

    @Test
    public void testGetSearchResults() {
        Assert.assertTrue(search.clickSearchButton(), "Failed to click search button");
        Assert.assertTrue(search.searchForUser("selenium"), "Failed to search for user");
        List<String> results = search.getSearchResults();
        Assert.assertFalse(results.isEmpty(), "Search results should not be empty");
    }

    @Test
    public void testClearSearchInput() {
        Assert.assertTrue(search.clickSearchButton(), "Failed to click search button");
        Assert.assertTrue(search.searchForUser("selenium"), "Failed to search for user");
        Assert.assertTrue(search.clearSearchInput(), "Failed to clear search input");
        List<String> results = search.getSearchResults();
        Assert.assertTrue(results.isEmpty(), "Search results should be empty after clearing input");
    }

    @Test
    public void testSearchForHashtag() {
        Assert.assertTrue(search.clickSearchButton(), "Failed to click search button");
        Assert.assertTrue(search.searchForHashtag("selenium"), "Failed to search for hashtag");
        Assert.assertTrue(search.isSearchResultsVisible(), "Hashtag search results not visible");
    }

    @Test
    public void testSearchForLocation() {
        Assert.assertTrue(search.clickSearchButton(), "Failed to click search button");
        Assert.assertTrue(search.searchForLocation("New York"), "Failed to search for location");
        Assert.assertTrue(search.isSearchResultsVisible(), "Location search results not visible");
    }

    @Test
    public void testVerifySearchResultContains() {
        Assert.assertTrue(search.clickSearchButton(), "Failed to click search button");
        Assert.assertTrue(search.searchForUser("selenium"), "Failed to search for user");
        Assert.assertTrue(search.verifySearchResultContains("selenium"), 
            "Search results should contain 'selenium'");
    }

    @Test
    public void testSearchWithSpecialCharacters() {
        Assert.assertTrue(search.clickSearchButton(), "Failed to click search button");
        Assert.assertTrue(search.searchForUser("selenium@test"), "Failed to search with special characters");
        Assert.assertTrue(search.isSearchResultsVisible(), "Search results not visible");
    }

    @Test
    public void testSearchWithNumbers() {
        Assert.assertTrue(search.clickSearchButton(), "Failed to click search button");
        Assert.assertTrue(search.searchForUser("selenium123"), "Failed to search with numbers");
        Assert.assertTrue(search.isSearchResultsVisible(), "Search results not visible");
    }

    @Test
    public void testSearchWithLongText() {
        Assert.assertTrue(search.clickSearchButton(), "Failed to click search button");
        String longText = "selenium".repeat(10);
        Assert.assertTrue(search.searchForUser(longText), "Failed to search with long text");
        Assert.assertTrue(search.isSearchResultsVisible(), "Search results not visible");
    }

    @Test
    public void testSearchWithEmptyString() {
        Assert.assertTrue(search.clickSearchButton(), "Failed to click search button");
        Assert.assertTrue(search.searchForUser(""), "Failed to search with empty string");
        List<String> results = search.getSearchResults();
        Assert.assertTrue(results.isEmpty(), "Search results should be empty for empty search");
    }

    @Test
    public void testSearchWithSpaces() {
        Assert.assertTrue(search.clickSearchButton(), "Failed to click search button");
        Assert.assertTrue(search.searchForUser("selenium testing"), "Failed to search with spaces");
        Assert.assertTrue(search.isSearchResultsVisible(), "Search results not visible");
    }

    @Test
    public void testSearchSequence() {
        // Test a sequence of search operations
        Assert.assertTrue(search.clickSearchButton(), "Failed to click search button");
        Assert.assertTrue(search.searchForUser("selenium"), "Failed to search for first term");
        Assert.assertTrue(search.clearSearchInput(), "Failed to clear search input");
        Assert.assertTrue(search.searchForHashtag("testing"), "Failed to search for hashtag");
        Assert.assertTrue(search.clearSearchInput(), "Failed to clear search input");
        Assert.assertTrue(search.searchForLocation("London"), "Failed to search for location");
    }

    @Test
    public void testSearchResultsCaseInsensitive() {
        Assert.assertTrue(search.clickSearchButton(), "Failed to click search button");
        Assert.assertTrue(search.searchForUser("SELENIUM"), "Failed to search with uppercase");
        Assert.assertTrue(search.verifySearchResultContains("selenium"), 
            "Search should be case insensitive");
    }

    @AfterMethod(alwaysRun = true)
    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }
} 