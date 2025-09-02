package com.golzstore.springstore.repositories;

import com.golzstore.springstore.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository  extends CrudRepository<User, Long> {

}
