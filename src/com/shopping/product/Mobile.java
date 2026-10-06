package com.shopping.product;

public class Mobile extends Electronics{
	
	
	private int storageGb;
	private int ramGb;
	
	public Mobile(String name, double price, int stock, int warrantyYears, int storageGb ,int ramGb) {
		super(name, price, stock, warrantyYears);
		this.storageGb = storageGb;
		this.ramGb = ramGb;
	}

	@Override
	public void display() {
		// TODO Auto-generated method stub
		super.display();
		System.out.println("Storage: " + storageGb + "GB storage");
		System.out.println("RAM: " + ramGb + "GB");
        System.out.println("Warranty: " + getWarrantyYears()+ " yr warranty");

	}

	
	
	

}
