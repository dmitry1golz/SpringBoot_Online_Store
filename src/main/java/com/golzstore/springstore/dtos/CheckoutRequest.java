package com.golzstore.springstore.dtos;

import com.golzstore.springstore.entities.Message;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.rmi.MarshalException;
import java.util.UUID;

@Data
public class CheckoutRequest {
    @NotNull(message = "Cart ID id required")
    private UUID cartId;
}
