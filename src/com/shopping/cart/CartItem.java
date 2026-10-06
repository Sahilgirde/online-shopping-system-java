package com.shopping.cart;

import com.shopping.product.Product;

public class CartItem {
	private Product product;
	private int quantity;

	public CartItem(Product product, int quantity) {
		this.product = product;
		this.quantity = quantity;
	}

	public Product getProduct() {
		return product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void addQuantity(int qty) {
		quantity += qty;
	}
	 public double getSubtotal() {
	        return product.calculateFinalPrice() * quantity;
	    }
}
