package com.golzstore.springstore.repositories;

import com.golzstore.springstore.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
}
