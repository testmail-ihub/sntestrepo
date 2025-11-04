package com.snc.users;

public class AdminUser extends BaseUser {
    public AdminUser(String name, String email) {
        super(name, email);
    }

    public void resetPassword() {
        // Simulate password reset logic
        System.out.println("Password has been reset for admin user: " + getName());
    }
}
