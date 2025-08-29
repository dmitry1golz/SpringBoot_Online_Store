package com.golzstore.springstore.repositories;

import com.golzstore.springstore.entities.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
}