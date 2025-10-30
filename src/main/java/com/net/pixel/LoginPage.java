package com.net.pixel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {

    private WebDriver driver;

    // Locators
    private By usernameField = By.id("username");
    private By passwordField = By.id("password");
    private By loginButton = By.id("login-btn");

    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    // Method to set username
    public void setUsername(String username) {
        WebElement element = driver.findElement(usernameField);
        element.clear();
        element.sendKeys(username);
    }

    // Method to set password
    public void setPassword(String password) {
        WebElement element = driver.findElement(passwordField);
        element.clear();
        element.sendKeys(password);
    }

    // Method to click login button
    public void clickLogin() {
        driver.findElement(loginButton).click();
    }
}
