package com.shopping.payment;

public class CardPayment extends Payment {
    private String cardNumber;

    public CardPayment(double amount, String cardNumber) {
        super(amount);
        this.cardNumber = cardNumber;
    }

    @Override
    public boolean pay() {
        if (cardNumber == null || cardNumber.length() != 16) {
            System.out.println("Enter the 16 digits Card number .");
            return false;
        }
        System.out.printf("Rs.%.2f Paymetent from Card  (****%s)%n",
                amount, cardNumber.substring(12));
        return true;
    }

    @Override
    public String getMethod() { return "Card"; }
}
