package com.shopping.main;

import java.util.Scanner;

import com.shopping.cart.Cart;
import com.shopping.cart.CartItem;
import com.shopping.customer.Customer;
import com.shopping.order.Order;
import com.shopping.payment.CardPayment;
import com.shopping.payment.CashOnDelivery;
import com.shopping.payment.Payment;
import com.shopping.payment.UPIPayment;
import com.shopping.product.Laptop;
import com.shopping.product.Mobile;
import com.shopping.product.Product;
import com.shopping.product.Shirt;

public class APPMain {

	static Scanner sc = new Scanner(System.in);

	// takes only a number, asks again if input is wrong
	static int readInt(String prompt) {
		System.out.print(prompt);
		while (!sc.hasNextInt()) {
			sc.next(); // throw away the wrong input
			System.out.print("Enter a valid number: ");
		}
		int n = sc.nextInt();
		sc.nextLine(); // remove the leftover Enter
		return n;
	}

	// print all products
	static void showProducts(Product[] catalog) {
		System.out.println("\n--- PRODUCTS ---");
		for (int i = 0; i < catalog.length; i++) {
			catalog[i].display(); // each product prints in its own way
		}
	}

	// find a product by id, returns null if not found
	static Product findById(Product[] catalog, int id) {
		for (int i = 0; i < catalog.length; i++) {
			if (catalog[i].getId() == id) {
				return catalog[i];
			}
		}
		return null;
	}

	// search products by name (small or capital letters both work)
	static void search(Product[] catalog) {
		System.out.print("Enter the product name: ");
		String key = sc.nextLine().trim().toLowerCase();
		boolean found = false;
		for (int i = 0; i < catalog.length; i++) {
			if (catalog[i].getName().toLowerCase().contains(key)) {
				catalog[i].display();
				found = true;
			}
		}
		if (!found) {
			System.out.println("Product not found.");
		}
	}

	static void checkout(Customer customer) {
		Cart cart = customer.getCart();
		if (cart.isEmpty()) {
			System.out.println("Cart is empty.");
			return;
		}
		cart.display();

		System.out.println("Payment: 1) UPI  2) Card  3) Cash on Delivery");
		int choice = readInt("Choice: ");
		double amount = cart.getTotal();
		Payment payment; // parent reference, can hold any payment type

		switch (choice) {
		case 1:
			System.out.print("UPI ID: ");
			payment = new UPIPayment(amount, sc.nextLine().trim());
			break;
		case 2:
			System.out.print("16 digit card number: ");
			payment = new CardPayment(amount, sc.nextLine().trim());
			break;
		case 3:
			payment = new CashOnDelivery(amount);
			break;
		default:
			System.out.println("Wrong payment choice. Enter a valid choice.");
			return;
		}

		// stock is reduced only if payment is successful
		if (!payment.pay()) {
			System.out.println("Payment failed. Try again.");
			return;
		}

		// payment done, now reduce the stock
		for (int i = 0; i < cart.getCount(); i++) {
			CartItem ci = cart.getItem(i);
			ci.getProduct().reduceStock(ci.getQuantity());
		}

		Order order = new Order(customer, payment);
		order.printReceipt();
		cart.clear();
	}

	public static void main(String[] args) {
		// one array holds different types of products (polymorphism)
		Product[] catalog = new Product[6];
		catalog[0] = new Laptop("Dell Inspiron", 50000, 5, 2, 16, "Intel i5");
		catalog[1] = new Laptop("HP Pavilion", 60000, 3, 2, 16, "Ryzen 5");
		catalog[2] = new Mobile("Redmi Note", 15000, 10, 1, 128, 8);
		catalog[3] = new Mobile("Samsung Galaxy", 30000, 6, 1, 256, 6);
		catalog[4] = new Shirt("Cotton Shirt", 800, 20, "M");
		catalog[5] = new Shirt("Linen Shirt", 1200, 15, "L");

		System.out.print("Enter your name: ");
		Customer customer = new Customer(sc.nextLine().trim());

		// menu keeps coming back until user selects 0
		boolean running = true;
		while (running) {
			System.out.println("\n===== SHOPPING MENU =====");
			System.out.println("1. Show Products");
			System.out.println("2. Search Products");
			System.out.println("3. Add to Cart");
			System.out.println("4. Show Cart");
			System.out.println("5. Remove from Cart");
			System.out.println("6. Checkout");
			System.out.println("0. Exit");
			int choice = readInt("Choice: ");

			switch (choice) {
			case 1:
				showProducts(catalog);
				break;
			case 2:
				search(catalog);
				break;
			case 3: {
				int id = readInt("Product ID: ");
				Product p = findById(catalog, id);
				if (p == null) {
					System.out.println("Product not found.");
				} else {
					int qty = readInt("Quantity: ");
					if (customer.getCart().addItem(p, qty)) {
						System.out.println("Added to cart successfully.");
					}
				}
				break;
			}
			case 4:
				customer.getCart().display();
				break;
			case 5: {
				int rid = readInt("Enter the product ID to remove: ");
				if (customer.getCart().removeItem(rid)) {
					System.out.println("Removed.");
				} else {
					System.out.println("Item not found in cart.");
				}
				break;
			}
			case 6:
				checkout(customer);
				break;
			case 0:
				running = false;
				System.out.println("Thank you for shopping!");
				break;
			default:
				System.out.println("Enter a valid choice.");
			}
		}
	}
}