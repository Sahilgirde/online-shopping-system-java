package com.shopping.product;

public abstract class Clothing extends Product{
	

	private String size;
	private static final double CLOTHING_GST = 0.05;

	public Clothing(String name, double price, int stock, String size) {
		super(name, price, stock);
		this.size = size;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}
	
	
	@Override
	public String getCategory() {
		// TODO Auto-generated method stub
		return "Clothing";
	}

	@Override
	public double calculateFinalPrice() {
		// TODO Auto-generated method stub
		return getPrice() + getPrice() * CLOTHING_GST;
	}

	public void display() {
		super.display();
		// TODO Auto-generated method stub
		
	}

}
