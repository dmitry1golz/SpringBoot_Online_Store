package com.golzstore.springstore.orders;

import com.golzstore.springstore.products.ProductDto;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderItemDto {
    private ProductDto product;
    private int quantity;
    private BigDecimal totalPrice;

}
