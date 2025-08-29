package com.golzstore.springstore.repositories;

import com.golzstore.springstore.entities.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Byte> {
}