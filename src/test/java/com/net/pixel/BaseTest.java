package com.net.pixel;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    protected WebDriver driver;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "path-to-chromedriver");
        driver = new ChromeDriver();
    }

    @BeforeMethod
    public void beforeMethod() {
        // Initialize any common setup before each test method
    }

    @AfterMethod
    public void afterMethod() {
        // Cleanup any resources after each test method
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
