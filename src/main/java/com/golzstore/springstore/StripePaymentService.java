package com.golzstore.springstore;

public class StripePaymentService implements PaymentService {
    @Override
    public void processPayment(double amount) {
        System.out.println("Payment Service: Stripe");
        System.out.println("Amount paid: " + amount);
    }
}


