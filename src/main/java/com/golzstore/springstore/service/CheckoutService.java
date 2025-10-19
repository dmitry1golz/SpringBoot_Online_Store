package com.golzstore.springstore.service;

import com.golzstore.springstore.dtos.CheckoutRequest;
import com.golzstore.springstore.dtos.CheckoutResponse;
import com.golzstore.springstore.entities.Order;
import com.golzstore.springstore.exceptions.CartEmptyException;
import com.golzstore.springstore.exceptions.CartNotFoundException;
import com.golzstore.springstore.repositories.CartRepository;
import com.golzstore.springstore.repositories.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CheckoutService {
    private final CartRepository cartRepository;
    private final OrderRepository orderRepository;
    private final AuthService authService;
    private final CartService cartService;

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

        cartService.clearCart(cart.getId());
        return new CheckoutResponse(order.getId());
    }

}
