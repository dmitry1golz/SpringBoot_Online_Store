package com.golzstore.springstore.payments;

import com.golzstore.springstore.orders.Order;
import com.golzstore.springstore.carts.CartEmptyException;
import com.golzstore.springstore.carts.CartNotFoundException;
import com.golzstore.springstore.carts.CartRepository;
import com.golzstore.springstore.orders.OrderRepository;
import com.golzstore.springstore.auth.AuthService;
import com.golzstore.springstore.carts.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class CheckoutService {
    private final CartRepository cartRepository;
    private final OrderRepository orderRepository;
    private final AuthService authService;
    private final CartService cartService;
    private final PaymentGateway paymentGateway;

    @Transactional
    public CheckoutResponse checkout(CheckoutRequest request) {
        var cart = cartRepository.getCartWithItems(request.getCartId()).orElse(null);
        if (cart == null) {
            throw new CartNotFoundException();
        }
        if (cart.isEmpty()) {
            throw new CartEmptyException();
        }
        var order = Order.fromCart(cart, authService.getCurrentUser());

        orderRepository.save(order);
        try {
            var session = paymentGateway.createCheckoutSession(order);
            cartService.clearCart(cart.getId());
            return new CheckoutResponse(order.getId(), session.getCheckoutUrl());
        } catch (PaymentException ex) {
            orderRepository.delete(order);
            throw ex;
        }
    }

    public void handleWebhookEvent(WebhookRequest request) {
        paymentGateway.parseWebhookRequest(request).ifPresent(paymentResult -> {
            var order = orderRepository.findById(paymentResult.getOrderId()).orElseThrow();
            order.setStatus(paymentResult.getOrderStatus());
            orderRepository.save(order);
        });
    }

}
