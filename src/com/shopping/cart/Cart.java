package com.shopping.cart;

import com.shopping.product.Product;

public class Cart {
	// max items cart can hold
	private static final int MAX_ITEMS = 20;

	private CartItem[] items = new CartItem[MAX_ITEMS];
	private int count = 0; // how many items are in cart right now

	// add product to cart, returns true if added
	public boolean addItem(Product p, int qty) {
		if (qty <= 0) {
			System.out.println("Quantity should be at least 1.");
			return false;
		}

		// check if product is already in cart
		int index = -1;
		for (int i = 0; i < count; i++) {
			if (items[i].getProduct().getId() == p.getId()) {
				index = i;
			}
		}

		int alreadyInCart = 0;
		if (index != -1) {
			alreadyInCart = items[index].getQuantity();
		}

		// old quantity + new quantity should not cross stock
		if (alreadyInCart + qty > p.getStock()) {
			System.out.println("Not enough stock for " + p.getName() + " (available: " + p.getStock() + ")");
			return false;
		}

		if (index != -1) {
			items[index].addQuantity(qty); // already there, so only increase quantity
		} else if (count == MAX_ITEMS) {
			System.out.println("Cart is full.");
			return false;
		} else {
			items[count] = new CartItem(p, qty); // new item goes in next empty slot
			count++;
		}
		return true;
	}

	// remove item from cart using product id
	public boolean removeItem(int productId) {
		for (int i = 0; i < count; i++) {
			if (items[i].getProduct().getId() == productId) {
				// move all items one step left to fill the gap
				for (int j = i; j < count - 1; j++) {
					items[j] = items[j + 1];
				}
				items[count - 1] = null; // last slot is duplicate now
				count--;
				return true;
			}
		}
		return false; // item not found
	}

	// total price of all items
	public double getTotal() {
		double total = 0;
		for (int i = 0; i < count; i++) {
			total += items[i].getSubtotal();
		}
		return total;
	}

	public CartItem getItem(int i) {
		return items[i];
	}

	public int getCount() {
		return count;
	}

	public boolean isEmpty() {
		return count == 0;
	}

	// empty the cart after checkout
	public void clear() {
		for (int i = 0; i < count; i++) {
			items[i] = null;
		}
		count = 0;
	}

	// show all items and total
	public void display() {
		if (count == 0) {
			System.out.println("Cart is empty.");
			return;
		}
		for (int i = 0; i < count; i++) {
			CartItem ci = items[i];
			System.out.printf("[%d] %-22s x%d = Rs.%.2f%n", ci.getProduct().getId(), ci.getProduct().getName(),
					ci.getQuantity(), ci.getSubtotal());
		}
		System.out.printf("TOTAL: Rs.%.2f%n", getTotal());
	}
}