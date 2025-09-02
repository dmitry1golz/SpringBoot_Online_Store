package com.golzstore.springstore.repositories;

import com.golzstore.springstore.entities.Profile;
import org.springframework.data.repository.CrudRepository;

public interface ProfileRepository extends CrudRepository<Profile, Long> {

}