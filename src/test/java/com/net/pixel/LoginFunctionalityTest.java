package com.net.pixel;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class LoginFunctionalityTest {

    @Test
    public void testLogin() throws InterruptedException {
        // Set up ChromeDriver
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        WebDriver driver = new ChromeDriver();

        // Navigate to login page
        driver.get("http://example.com/login");

        // Enter username and password
        WebElement usernameField = driver.findElement(By.id("username"));
        usernameField.sendKeys("testuser");
        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys("testpassword");

        // Click login button
        WebElement loginButton = driver.findElement(By.id("login-button"));
        loginButton.click();

        // Wait for login to complete
        Thread.sleep(2000);

        // Assert that login was successful
        String expectedUrl = "http://example.com/dashboard";
        String actualUrl = driver.getCurrentUrl();
        assertEquals(expectedUrl, actualUrl, "Login was not successful");

        // Close the browser
        driver.quit();
    }
}
