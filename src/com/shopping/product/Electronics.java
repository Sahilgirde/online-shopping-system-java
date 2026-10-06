package com.shopping.product;

public abstract class Electronics extends Product {
	private int warrantyYears;

	public Electronics(String name, double price, int stock, int warrantyYears) {
		super(name, price, stock);
		this.warrantyYears = warrantyYears;
		// TODO Auto-generated constructor stub
	}

	public int getWarrantyYears() {
		return warrantyYears;
	}

	@Override
	public String getCategory() {
		return "Electronics";
	}

	@Override
	public double calculateFinalPrice() {
		return getPrice() + getPrice() * GST_Rate; 
	}

}
