package com.golzstore.springstore.service;

import com.golzstore.springstore.entities.Order;

public interface PaymentGateway {
    CheckoutSession createCheckoutSession(Order order);
}
