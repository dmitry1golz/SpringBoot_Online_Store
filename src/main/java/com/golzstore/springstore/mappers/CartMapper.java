package com.golzstore.springstore.mappers;

import com.golzstore.springstore.dtos.CartDto;
import com.golzstore.springstore.entities.Cart;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CartMapper {
    CartDto toDto(Cart cart);
}
