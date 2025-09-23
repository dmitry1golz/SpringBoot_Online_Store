package com.golzstore.springstore.mappers;

import com.golzstore.springstore.dtos.CartDto;
import com.golzstore.springstore.dtos.CartItemDto;
import com.golzstore.springstore.entities.Cart;
import com.golzstore.springstore.entities.CartItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CartMapper {
    @Mapping(target = "totalPrice", expression = "java(cart.getTotalPrice())")
    CartDto toDto(Cart cart);

    @Mapping(target = "totalPrice", expression = "java(cartItem.getTotalPrice())")
    CartItemDto toDto(CartItem cartItem);


}
