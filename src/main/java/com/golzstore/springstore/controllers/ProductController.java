package com.golzstore.springstore.controllers;

import com.golzstore.springstore.dtos.ProductDto;
import com.golzstore.springstore.entities.Product;
import com.golzstore.springstore.mappers.ProductMapper;
import com.golzstore.springstore.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor


@RequestMapping("/products")
public class ProductController {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @GetMapping
    public List<ProductDto> getAllProducts(@RequestParam(name = "categoryId", required = false) Byte categoryId) {
        List<Product> products;
        if (categoryId != null) {
            products = productRepository.findByCategoryId(categoryId);
        } else {
            products = productRepository.findAllWithCategory();
        }

        return products.stream()
                .map(productMapper::toDto)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable Long id) {

        var product = productRepository.findById(id)
                .orElse(null);
        if (product == null) {
            return ResponseEntity.notFound()
                    .build();
        }
        return ResponseEntity.ok(productMapper.toDto(product));
    }

}
