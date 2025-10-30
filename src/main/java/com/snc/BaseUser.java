package com.snc;

public abstract class BaseUser {
    protected String name;
    protected String email;

    public BaseUser(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getUserInfo() {
        return "Name: " + name + ", Email: " + email;
    }
}
