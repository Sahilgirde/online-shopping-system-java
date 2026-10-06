package com.shopping.order;

import com.shopping.cart.Cart;
import com.shopping.cart.CartItem;
import com.shopping.customer.Customer;
import com.shopping.payment.Payment;

public class Order {
	private static int orderCounter = 5000; // shared counter, gives each order a new id

	private final int orderId;
	private final String customerName;
	private final CartItem[] items;
	private final double total;
	private final String paymentMethod;

	public Order(Customer customer, Payment payment) {
		this.orderId = ++orderCounter; // first order gets 5001
		this.customerName = customer.getName();

		// copy items from cart, because cart will be cleared after checkout
		Cart cart = customer.getCart();
		this.items = new CartItem[cart.getCount()];
		for (int i = 0; i < items.length; i++) {
			items[i] = cart.getItem(i);
		}
		this.total = cart.getTotal();
		this.paymentMethod = payment.getMethod();
	}

	// prints the bill after checkout
	public void printReceipt() {
		System.out.println("\n===== ORDER RECEIPT =====");
		System.out.println("Order ID : " + orderId);
		System.out.println("Customer : " + customerName);
		for (int i = 0; i < items.length; i++) {
			System.out.println("  " + items[i].getProduct().getName() + " x" + items[i].getQuantity() + "  Rs."
					+ items[i].getSubtotal());
		}
		System.out.println("Total    : Rs." + total);
		System.out.println("Payment  : " + paymentMethod);
		System.out.println("=========================\n");
	}
}