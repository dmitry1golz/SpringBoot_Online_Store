package com.golzstore.springstore.admin;

import com.golzstore.springstore.common.SecurityRules;
import com.golzstore.springstore.users.Role;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.stereotype.Component;

@Component
public class AdminSecurityRules implements SecurityRules {
    @Override
    public void configure(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry registry) {

        registry.requestMatchers("/admin/**").hasRole(Role.ADMIN.name());

    }
}
