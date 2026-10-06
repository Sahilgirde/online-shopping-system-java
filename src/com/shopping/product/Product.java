package com.shopping.product;

public abstract class Product {

	private static int idCounter = 1000;

	public static final double GST_Rate = 0.18;

	private final int id;
	private String name;
	private double price;
	private int stock;

	public Product(String name, double price, int stock) {
		this.id = ++idCounter;
		this.name = name;
		this.price = price;
		this.stock = stock;
	}

	public abstract String getCategory();
    public abstract double calculateFinalPrice();
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public int getId() {
		return id;
	}
	
	public void display() {
	    System.out.println("ID: " + id);
	    System.out.println("Name: " + name);
	    System.out.println("Category: " + getCategory());
	    System.out.println("Price: Rs." + calculateFinalPrice()+ " (Including GST)");
	    System.out.println("Stock: " + stock);
	}

	public boolean reduceStock(int qty) {
	    if (qty > stock) {
	        return false;
	    }
	    stock -= qty;
	    return true;
	}
}
