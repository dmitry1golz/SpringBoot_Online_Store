package com.golzstore.springstore.repositories;

import com.golzstore.springstore.entities.Order;
import com.golzstore.springstore.entities.User;
import com.sun.source.tree.ParameterizedTypeTree;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @EntityGraph(attributePaths = "items.product")
    @Query("select o from Order o where o.customer = :customer")
    List<Order> getAllByCustomer(User customer);
}