package com.Instagram.suggest;

import java.time.Duration;
import java.util.List;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.InstagramBase.Base;

public class Suggest extends Base {
    WebDriver driver;
    WebDriverWait wait;

    public Suggest(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public boolean navigateToExplore() {
        try {
            // Click on the explore icon
            WebElement exploreIcon = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[@href='/explore/']")));
            exploreIcon.click();
            Thread.sleep(2000);
            return true;
        } catch (Exception e) {
            System.out.println("Error navigating to explore: " + e.getMessage());
            return false;
        }
    }

    public List<String> getSuggestedAccounts() {
        List<String> suggestedAccounts = new ArrayList<>();
        try {
            // Wait for suggested accounts to load
            wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//div[contains(@class, 'x1qjc9v5')]//span[contains(@class, '_ap3a')]")));
            
            // Get all suggested account names
            List<WebElement> accountElements = driver.findElements(
                By.xpath("//div[contains(@class, 'x1qjc9v5')]//span[contains(@class, '_ap3a')]"));
            
            for (WebElement account : accountElements) {
                suggestedAccounts.add(account.getText());
            }
        } catch (Exception e) {
            System.out.println("Error getting suggested accounts: " + e.getMessage());
        }
        return suggestedAccounts;
    }

    public boolean followSuggestedAccount(String accountName) {
        try {
            // Find the follow button for the specified account
            WebElement followButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//span[text()='" + accountName + "']/ancestor::div[contains(@class, 'x1qjc9v5')]//button[contains(text(), 'Follow')]")));
            followButton.click();
            Thread.sleep(2000);
            return true;
        } catch (Exception e) {
            System.out.println("Error following account: " + e.getMessage());
            return false;
        }
    }

    public boolean unfollowSuggestedAccount(String accountName) {
        try {
            // Find the unfollow button for the specified account
            WebElement unfollowButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//span[text()='" + accountName + "']/ancestor::div[contains(@class, 'x1qjc9v5')]//button[contains(text(), 'Following')]")));
            unfollowButton.click();
            Thread.sleep(1000);
            
            // Click confirm unfollow
            WebElement confirmUnfollow = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(text(), 'Unfollow')]")));
            confirmUnfollow.click();
            Thread.sleep(2000);
            return true;
        } catch (Exception e) {
            System.out.println("Error unfollowing account: " + e.getMessage());
            return false;
        }
    }

    public boolean searchAndFollow(String searchTerm) {
        try {
            // Click search icon
            WebElement searchIcon = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[@href='/explore/']")));
            searchIcon.click();
            Thread.sleep(2000);

            // Enter search term
            WebElement searchInput = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//input[@placeholder='Search']")));
            searchInput.sendKeys(searchTerm);
            Thread.sleep(2000);

            // Click on first result
            WebElement firstResult = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[contains(@class, 'x1qjc9v5')]//span[contains(@class, '_ap3a')]")));
            firstResult.click();
            Thread.sleep(2000);

            // Click follow button
            WebElement followButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(text(), 'Follow')]")));
            followButton.click();
            Thread.sleep(2000);
            return true;
        } catch (Exception e) {
            System.out.println("Error in search and follow: " + e.getMessage());
            return false;
        }
    }
} 