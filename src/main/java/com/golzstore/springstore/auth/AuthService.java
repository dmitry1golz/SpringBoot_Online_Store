package com.golzstore.springstore.auth;

import com.golzstore.springstore.users.User;
import com.golzstore.springstore.users.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
@AllArgsConstructor
@Service
public class AuthService {

    private final UserRepository userRepository;
    public User getCurrentUser() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var userId = (Long) authentication.getPrincipal();

        return userRepository.findById(userId).orElse(null);
    }

}
