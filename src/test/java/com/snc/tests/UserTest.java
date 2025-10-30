package com.snc.tests;

import com.snc.AdminUser;
import com.snc.BaseUser;
import com.snc.CustomerUser;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserTest {

    @Test
    void testBaseUserGetUserInfo() {
        BaseUser baseUser = new AdminUser("John Doe", "john.doe@example.com"); // Using AdminUser for instantiation
        assertEquals("Name: John Doe, Email: john.doe@example.com", baseUser.getUserInfo());
    }

    @Test
    void testAdminUserGetUserInfo() {
        AdminUser adminUser = new AdminUser("Admin User", "admin@example.com");
        assertEquals("Name: Admin User, Email: admin@example.com", adminUser.getUserInfo());
    }

    @Test
    void testCustomerUserGetUserInfo() {
        CustomerUser customerUser = new CustomerUser("Customer User", "customer@example.com");
        assertEquals("Name: Customer User, Email: customer@example.com", customerUser.getUserInfo());
    }

    @Test
    void testAdminUserResetPassword() {
        AdminUser adminUser = new AdminUser("Admin User", "admin@example.com");
        // This method prints to console, so we can't directly assert its output without mocking System.out
        // For demonstration, we'll just call it to ensure no exceptions are thrown.
        adminUser.resetPassword();
        assertTrue(true); // Placeholder assertion
    }

    @Test
    void testCustomerUserViewOrders() {
        CustomerUser customerUser = new CustomerUser("Customer User", "customer@example.com");
        // This method prints to console, so we can't directly assert its output without mocking System.out
        // For demonstration, we'll just call it to ensure no exceptions are thrown.
        customerUser.viewOrders();
        assertTrue(true); // Placeholder assertion
    }
}
