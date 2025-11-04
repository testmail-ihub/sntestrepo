package com.snc.users;

public class CustomerUser extends BaseUser {
    public CustomerUser(String name, String email) {
        super(name, email);
    }

    public void viewOrders() {
        // Simulate viewing orders logic
        System.out.println("Viewing orders for customer user: " + getName());
    }
}
