package com.golzstore.springstore;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;


@Configuration
public class AppConfig {
    @Value("${payment-service:stripe}")
    private String paymentService;

    @Bean
    public PaymentService stripe() {
        return new StripePaymentService();
    }

    @Bean
    public PaymentService paypal() {
        return new PayPalPaymentService();
    }

    @Bean
    public OrderService orderService() {
        if (paymentService.equals("stripe")) {
            return new OrderService(stripe());
        }
        return new OrderService(paypal());
    }
}
