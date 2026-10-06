package com.shopping.product;

public class Shirt extends Clothing {
	private static final double SHIRT_DISCOUNT = 0.10;

	public Shirt(String name, double price, int stock, String size) {
		super(name, price, stock, size);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double calculateFinalPrice() {
		double base = super.calculateFinalPrice();
		return base - base * SHIRT_DISCOUNT;

	}

	@Override
	public void display() {
		// TODO Auto-generated method stub
		super.display();
		System.out.println("Size: " + getSize());
	}

}
