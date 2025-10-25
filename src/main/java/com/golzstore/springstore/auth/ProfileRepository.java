package com.golzstore.springstore.auth;

import com.golzstore.springstore.users.Profile;
import org.springframework.data.repository.CrudRepository;

public interface ProfileRepository extends CrudRepository<Profile, Long> {

}