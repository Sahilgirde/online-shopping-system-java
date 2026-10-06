package com.shopping.product;

public class Laptop extends Electronics {
	private static final double LAPTOP_DISCOUNT = 0.05; // 5% off
	private int ramGb;
	private String processor;
	public Laptop(String name, double price, int stock, int warrantyYears, int ramGb, String processor) {
		super(name, price, stock, warrantyYears);
		this.ramGb = ramGb;
		this.processor = processor;
	}
	
	 @Override
	    public double calculateFinalPrice() {
	        double withGst = super.calculateFinalPrice();
	        return withGst - withGst * LAPTOP_DISCOUNT;
	    }
	 @Override
	    public void display() {
	        super.display();
	        System.out.println("RAM: " + ramGb + "GB RAM");
	        System.out.println("Processor: " + processor);
	        System.out.println("Warranty: " + getWarrantyYears()+ " yr warranty");
	    }
}
