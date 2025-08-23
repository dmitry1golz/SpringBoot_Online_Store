package com.golzstore.springstore;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class InMemoryUserRepository implements UserRepository {
    private final Map<String, User> users = new HashMap<>();

    @Override
    public void save(User user) {
        users.put(user.getEmail(), user);
        System.out.println("Saved User: " + user.getName());
    }

    @Override
    public User findByEmail(String email) {
        return users.getOrDefault(email, null);
    }
}
