package com.snc;

public class AdminUser extends BaseUser {

    public AdminUser(String name, String email) {
        super(name, email);
    }

    public void resetPassword() {
        System.out.println("Admin " + name + " is resetting a user's password.");
    }
}
