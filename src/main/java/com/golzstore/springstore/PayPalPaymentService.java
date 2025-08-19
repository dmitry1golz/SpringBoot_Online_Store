package com.golzstore.springstore;

import org.springframework.stereotype.Service;

@Service
public class PayPalPaymentService implements PaymentService {
    @Override
    public void processPayment(double amount) {
        System.out.println("Payment Service: PayPal");
        System.out.printf("Amount paid: " + amount);
}



}
