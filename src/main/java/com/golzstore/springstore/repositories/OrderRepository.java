package com.golzstore.springstore.repositories;

import com.golzstore.springstore.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}