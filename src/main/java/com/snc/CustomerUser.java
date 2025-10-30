package com.snc;

public class CustomerUser extends BaseUser {

    public CustomerUser(String name, String email) {
        super(name, email);
    }

    public void viewOrders() {
        System.out.println("Customer " + name + " is viewing their orders.");
    }
}
