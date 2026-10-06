package com.shopping.payment;

public class UPIPayment extends Payment {
	private String upiId;

	public UPIPayment(double amount, String upiId) {
		super(amount); // send amount to Payment class
		this.upiId = upiId;
	}

	// checks the UPI id and completes the payment
	@Override
	public boolean pay() {
		// a valid UPI id must have @ in it, like sahil@upi
		if (upiId == null || !upiId.contains("@")) {
			System.out.println("Wrong UPI ID (@ is required).");
			return false;
		}
		System.out.println("Rs." + amount + " paid using UPI (" + upiId + ")");
		return true;
	}

	@Override
	public String getMethod() {
		return "UPI";
	}
}