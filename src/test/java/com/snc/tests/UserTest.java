package com.snc.tests;

import com.snc.users.BaseUser;
import com.snc.users.AdminUser;
import com.snc.users.CustomerUser;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    @Test
    void testBaseUserGetUserInfo() {
        BaseUser user = new BaseUser("Alice", "alice@example.com");
        String info = user.getUserInfo();
        assertEquals("Name: Alice, Email: alice@example.com", info);
    }

    @Test
    void testAdminUserResetPassword() {
        AdminUser admin = new AdminUser("Bob", "bob@example.com");
        assertDoesNotThrow(() -> admin.resetPassword());
    }

    @Test
    void testCustomerUserViewOrders() {
        CustomerUser customer = new CustomerUser("Carol", "carol@example.com");
        assertDoesNotThrow(() -> customer.viewOrders());
    }
}
