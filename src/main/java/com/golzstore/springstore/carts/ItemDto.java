package com.golzstore.springstore.carts;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ItemDto {
    private ProductDto product;
    private int quantity;
    private BigDecimal totalPrice;
}
