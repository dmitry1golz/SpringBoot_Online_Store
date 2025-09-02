package com.golzstore.springstore.repositories;

import com.golzstore.springstore.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}