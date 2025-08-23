package com.golzstore.springstore;

public interface UserRepository {
    void save(User user);
    User findByEmail(String email);
}
