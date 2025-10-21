package com.golzstore.springstore.controllers;

import com.golzstore.springstore.dtos.ErrorDto;
import com.golzstore.springstore.dtos.OrderDto;
import com.golzstore.springstore.exceptions.OrderNotFoundException;
import com.golzstore.springstore.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    @GetMapping
    public List<OrderDto> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/{orderId}")
    public OrderDto getOrder(@PathVariable Long orderId) {
        return orderService.getOrder(orderId);
    }

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<Void> handleOrderNotFound() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorDto> handleAccessDenied(Exception ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorDto(ex.getMessage()));

    }
}
