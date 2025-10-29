package com.net.pixel.tests;

import com.net.pixel.config.ConfigManager;
import com.net.pixel.pages.LoginPage;
import com.net.pixel.utils.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class LoginTest {
    private WebDriver driver;
    private LoginPage loginPage;

    @BeforeMethod
    public void setUp() {
        driver = WebDriverFactory.getDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(ConfigManager.getImplicitWait()));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(ConfigManager.getPageLoadTimeout()));
        driver.manage().window().maximize();
        loginPage = new LoginPage(driver);
    }

    @Test
    public void testSuccessfulLogin() {
        loginPage.navigateTo(ConfigManager.getBaseUrl());
        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLoginButton();
        Assert.assertTrue(loginPage.isLoginSuccessful(), "Login was not successful!");
    }

    @Test
    public void testFailedLoginInvalidCredentials() {
        loginPage.navigateTo(ConfigManager.getBaseUrl());
        loginPage.enterUsername("invalid_user");
        loginPage.enterPassword("wrong_password");
        loginPage.clickLoginButton();
        Assert.assertFalse(loginPage.isLoginSuccessful(), "Login should have failed with invalid credentials!");
        Assert.assertTrue(loginPage.getErrorMessage().contains("Epic sadface"), "Error message not found or incorrect!");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}