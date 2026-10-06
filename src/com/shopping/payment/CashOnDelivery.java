package com.shopping.payment;

public class CashOnDelivery extends Payment {

	public CashOnDelivery(double amount) {
		super(amount); // send amount to Payment class
	}

	// no check needed here, money is taken when the product arrives
	@Override
	public boolean pay() {
		System.out.println("Rs." + amount + " will be collected on delivery.");
		return true;
	}

	@Override
	public String getMethod() {
		return "Cash on Delivery";
	}
}