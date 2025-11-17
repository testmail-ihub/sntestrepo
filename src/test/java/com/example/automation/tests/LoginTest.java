package com.example.automation.tests;

import com.example.automation.base.BaseTest;
import com.example.automation.pages.HomePage;
import com.example.automation.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test
    public void testSuccessfulLogin() {
        driver.get("http://www.example.com/login"); // TODO: Replace with actual login URL

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("testuser", "testpassword"); // TODO: Replace with actual credentials

        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isWelcomeMessageDisplayed(), "Welcome message not displayed after login.");
        Assert.assertEquals(homePage.getWelcomeMessageText(), "Welcome, testuser!", "Welcome message text is incorrect.");
    }
}