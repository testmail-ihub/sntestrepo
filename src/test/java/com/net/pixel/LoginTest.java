package com.net.pixel;
import org.testng.annotations.Test;
public class LoginTest extends BaseTest {

    @Test
    public void testSuccessfulLogin() {
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);

        // Open the login page
        driver.get("http://example.com/login");

        // Enter username and password
        loginPage.setUsername("testuser");
        loginPage.setPassword("testpassword");

        // Click login
        loginPage.clickLogin();

        // Validate successful login
        homePage.validateSuccessfulLogin();
    }
}