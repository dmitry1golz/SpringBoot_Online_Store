package com.golzstore.springstore.controllers;

import com.golzstore.springstore.dtos.CartDto;
import com.golzstore.springstore.entities.Cart;
import com.golzstore.springstore.repositories.CartRepository;
import com.golzstore.springstore.mappers.CartMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


@AllArgsConstructor
@RestController
@RequestMapping("/carts")
public class CartController {
    private final CartRepository cartRepository;
    private final CartMapper cartMapper;

    @PostMapping
    public ResponseEntity<CartDto> createCart(
            UriComponentsBuilder uriBuilder
    ) {
        var cart = new Cart();
        cartRepository.save(cart);

        var cartDto = cartMapper.toDto(cart);
        var uri = uriBuilder.path("/carts/{id}").buildAndExpand(cartDto).toUri();

        return ResponseEntity.created(uri).body(cartDto);

    }

//    @PostMapping
//    public ResponseEntity<productDto> addItem() {}

}
