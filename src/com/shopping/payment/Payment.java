package com.shopping.payment;

public abstract class Payment {
    protected double amount;

    public Payment(double amount) { this.amount = amount; }

    // true = payment ho gayi, false = fail
    public abstract boolean pay();
    public abstract String getMethod();
}
