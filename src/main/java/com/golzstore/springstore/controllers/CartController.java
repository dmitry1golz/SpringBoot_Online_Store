package com.golzstore.springstore.controllers;

import com.golzstore.springstore.dtos.AddItemToCartRequest;
import com.golzstore.springstore.dtos.CartDto;
import com.golzstore.springstore.dtos.CartItemDto;
import com.golzstore.springstore.entities.Cart;
import com.golzstore.springstore.entities.CartItem;
import com.golzstore.springstore.mappers.CartMapper;
import com.golzstore.springstore.repositories.CartRepository;
import com.golzstore.springstore.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.UUID;


@AllArgsConstructor
@RestController
@RequestMapping("/carts")
public class CartController {
    private final CartRepository cartRepository;
    private final CartMapper cartMapper;
    private final ProductRepository productRepository;

    @PostMapping
    public ResponseEntity<CartDto> createCart(UriComponentsBuilder uriBuilder) {
        var cart = new Cart();
        cartRepository.save(cart);

        var cartDto = cartMapper.toDto(cart);
        var uri = uriBuilder.path("/carts/{id}").buildAndExpand(cartDto).toUri();

        return ResponseEntity.created(uri).body(cartDto);

    }

    @PostMapping("/{cartId}/items")
    public ResponseEntity<CartItemDto> addToCart(
            @PathVariable UUID cartId,
            @RequestBody AddItemToCartRequest request) {
        var cart = cartRepository.findById(cartId).orElse(null);
        if (cart == null) {
            return ResponseEntity.notFound().build();
        }

        var product = productRepository.findById(request.getProductId()).orElse(null);
        if (product == null) {
            return ResponseEntity.badRequest().build();
        }

        var cartItem = cart.getCartItems().stream()
                           .filter(item -> item.getProduct().getId().equals(product.getId()))
                           .findFirst()
                           .orElse(null);
        if (cartItem != null) {
            cartItem.setQuantity(cartItem.getQuantity() + 1);
        }
        else {
            cartItem = new CartItem();
            cartItem.setProduct(product);
            cartItem.setQuantity(1);
            cartItem.setCart(cart);
            cart.getCartItems().add(cartItem);
        }
        cartRepository.save(cart);
        var cartItemDto = cartMapper.toDto(cartItem);

        return ResponseEntity.status(HttpStatus.CREATED).body(cartItemDto);

    }

}
