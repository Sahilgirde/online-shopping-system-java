package com.shopping.customer;

import com.shopping.cart.Cart;

public class Customer {
    private String name;
    private Cart cart = new Cart();

    public Customer(String name) { this.name = name; }

    public String getName() { return name; }
    public Cart getCart() { return cart; }
}
