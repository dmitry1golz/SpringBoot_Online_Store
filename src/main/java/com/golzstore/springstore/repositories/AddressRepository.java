package com.golzstore.springstore.repositories;

import com.golzstore.springstore.entities.Address;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Address, Long> {
}