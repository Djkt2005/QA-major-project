package com.Instagram.search;

import java.time.Duration;
import java.util.List;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.Instagram.base.Base;

public class Search extends Base {
    WebDriver driver;
    WebDriverWait wait;

    public Search(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public boolean clickSearchButton() {
        try {
            WebElement searchBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//span[text()='Search']")));
            searchBtn.click();
            return true;
        } catch (Exception e) {
            System.out.println("Error clicking search button: " + e.getMessage());
            return false;
        }
    }

    public boolean searchForUser(String username) {
        try {
            WebElement searchInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//input[@placeholder='Search']")));
            searchInput.clear();
            searchInput.sendKeys(username);
            return true;
        } catch (Exception e) {
            System.out.println("Error searching for user: " + e.getMessage());
            return false;
        }
    }

    public boolean clickFirstSearchResult() {
        try {
            WebElement firstResult = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[contains(@class, 'x9f619 x78zum5 xdt5ytf x1iyjqo2 x6ikm8r x1odjw0f xh8yej3 xocp1fn')]/a[1]")));
            firstResult.click();
            return true;
        } catch (Exception e) {
            System.out.println("Error clicking first search result: " + e.getMessage());
            return false;
        }
    }

    public List<String> getSearchResults() {
        List<String> results = new ArrayList<>();
        try {
            List<WebElement> searchResults = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.xpath("//div[contains(@class, 'x9f619 x78zum5 xdt5ytf x1iyjqo2 x6ikm8r x1odjw0f xh8yej3 xocp1fn')]//span[contains(@class, '_ap3a')]")));
            
            for (WebElement result : searchResults) {
                results.add(result.getText());
            }
        } catch (Exception e) {
            System.out.println("Error getting search results: " + e.getMessage());
        }
        return results;
    }

    public boolean clearSearchInput() {
        try {
            WebElement searchInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//input[@placeholder='Search']")));
            searchInput.clear();
            return true;
        } catch (Exception e) {
            System.out.println("Error clearing search input: " + e.getMessage());
            return false;
        }
    }

    public boolean isSearchResultsVisible() {
        try {
            return wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//div[contains(@class, 'x9f619 x78zum5 xdt5ytf x1iyjqo2 x6ikm8r x1odjw0f xh8yej3 xocp1fn')]"))).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean searchForHashtag(String hashtag) {
        try {
            WebElement searchInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//input[@placeholder='Search']")));
            searchInput.clear();
            searchInput.sendKeys("#" + hashtag);
            return true;
        } catch (Exception e) {
            System.out.println("Error searching for hashtag: " + e.getMessage());
            return false;
        }
    }

    public boolean searchForLocation(String location) {
        try {
            WebElement searchInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//input[@placeholder='Search']")));
            searchInput.clear();
            searchInput.sendKeys(location);
            return true;
        } catch (Exception e) {
            System.out.println("Error searching for location: " + e.getMessage());
            return false;
        }
    }

    public boolean verifySearchResultContains(String expectedText) {
        try {
            List<WebElement> searchResults = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.xpath("//div[contains(@class, 'x9f619 x78zum5 xdt5ytf x1iyjqo2 x6ikm8r x1odjw0f xh8yej3 xocp1fn')]//span[contains(@class, '_ap3a')]")));
            
            for (WebElement result : searchResults) {
                if (result.getText().toLowerCase().contains(expectedText.toLowerCase())) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            System.out.println("Error verifying search result: " + e.getMessage());
            return false;
        }
    }
} 