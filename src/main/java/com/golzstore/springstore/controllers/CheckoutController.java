package com.golzstore.springstore.controllers;

import com.golzstore.springstore.dtos.CheckoutRequest;
import com.golzstore.springstore.dtos.CheckoutResponse;
import com.golzstore.springstore.dtos.ErrorDto;
import com.golzstore.springstore.entities.Order;
import com.golzstore.springstore.entities.OrderItem;
import com.golzstore.springstore.entities.OrderStatus;
import com.golzstore.springstore.repositories.CartRepository;
import com.golzstore.springstore.repositories.OrderRepository;
import com.golzstore.springstore.service.AuthService;
import com.golzstore.springstore.service.CartService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@AllArgsConstructor
@RestController
@RequestMapping("/checkout")
public class CheckoutController {

    private final CartRepository cartRepository;
    private final AuthService authService;
    private final OrderRepository orderRepository;
    private final CartService cartService;

    @PostMapping
    public ResponseEntity<?> checkout(@Valid @RequestBody CheckoutRequest request) {
        var cart = cartRepository.getCartWithItems(request.getCartId()).orElse(null);
        if (cart == null) {
            return ResponseEntity.badRequest()
                    .body(new ErrorDto("Cart not found"));
        }
        if (cart.getItems().isEmpty()) {
            return ResponseEntity.badRequest()
                    .body(new ErrorDto("Cart is empty"));
        }

        var order = new Order();
        order.setTotalPrice(cart.getTotalPrice());
        order.setStatus(OrderStatus.PENDING);
        order.setCustomer(authService.getCurrentUser());

        cart.getItems().forEach(item -> {
            var orderItem = new OrderItem();

            orderItem.setOrder(order);
            orderItem.setProduct(item.getProduct());
            orderItem.setQuantity(item.getQuantity());
            orderItem.setTotalPrice(item.getTotalPrice());
            orderItem.setUnitPrice(item.getProduct().getPrice());
            order.getItems().add(orderItem);
        });
        orderRepository.save(order);

        cartService.clearCart(cart.getId());
        return ResponseEntity.ok(new CheckoutResponse(order.getId()));
    }


}
