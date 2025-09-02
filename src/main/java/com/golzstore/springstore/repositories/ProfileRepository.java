package com.golzstore.springstore.repositories;

import com.golzstore.springstore.dtos.UserSummary;
import com.golzstore.springstore.entities.Profile;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProfileRepository extends CrudRepository<Profile, Long> {

}