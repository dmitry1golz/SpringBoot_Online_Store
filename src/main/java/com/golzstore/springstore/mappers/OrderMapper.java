package com.golzstore.springstore.mappers;

import com.golzstore.springstore.dtos.OrderDto;
import com.golzstore.springstore.entities.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderDto toDto(Order order);
}
