package com.golzstore.springstore.controllers;

import com.golzstore.springstore.dtos.CheckoutRequest;
import com.golzstore.springstore.dtos.CheckoutResponse;
import com.golzstore.springstore.dtos.ErrorDto;
import com.golzstore.springstore.exceptions.CartEmptyException;
import com.golzstore.springstore.exceptions.CartNotFoundException;
import com.golzstore.springstore.exceptions.PaymentException;
import com.golzstore.springstore.service.CheckoutService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@AllArgsConstructor
@RestController
@RequestMapping("/checkout")
public class CheckoutController {
    private final CheckoutService checkoutService;

    @PostMapping
    public CheckoutResponse checkout(@Valid @RequestBody CheckoutRequest request) {
        return checkoutService.checkout(request);

    }

    @ExceptionHandler(PaymentException.class)
    public ResponseEntity<?> handlePaymentException() {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorDto("Error creating a checkout session"));

    }

    @ExceptionHandler({CartNotFoundException.class, CartEmptyException.class})
    public ResponseEntity<ErrorDto> handlerException(Exception ex) {
        return ResponseEntity
                .badRequest()
                .body(new ErrorDto(ex.getMessage()));
    }

}
