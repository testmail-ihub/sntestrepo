package com.example.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {
    private WebDriver driver;

    // Locators (example: a welcome message or a logout button)
    private By welcomeMessage = By.id("welcomeMessage");
    private By logoutButton = By.id("logoutButton");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    // Actions
    public boolean isWelcomeMessageDisplayed() {
        return driver.findElement(welcomeMessage).isDisplayed();
    }

    public void clickLogoutButton() {
        driver.findElement(logoutButton).click();
    }

    public String getWelcomeMessageText() {
        return driver.findElement(welcomeMessage).getText();
    }
}