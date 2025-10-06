package com.net.pixel;

public class AddTwoNumbers {
    public static void main(String[] args) {
        int num1 = 2;
        int num2 = 3;
        int sum = add(num1, num2);
        System.out.println("The sum of " + num1 + " and " + num2 + " is: " + sum);
    }

    public static int add(int a, int b) {
        return a + b;
    }
}