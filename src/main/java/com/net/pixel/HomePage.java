package com.net.pixel;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
public class HomePage {
    private WebDriver driver;
    private By welcomeMessage = By.id("welcome-message");
    private By dashboardElement = By.id("dashboard");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isWelcomeMessageDisplayed() {
        try {
            return driver.findElement(welcomeMessage).isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    public boolean isDashboardDisplayed() {
        try {
            return driver.findElement(dashboardElement).isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    public void validateSuccessfulLogin() {
        Assert.assertTrue(isWelcomeMessageDisplayed(), "Welcome message is not displayed");
        Assert.assertTrue(isDashboardDisplayed(), "Dashboard is not displayed");
    }
}