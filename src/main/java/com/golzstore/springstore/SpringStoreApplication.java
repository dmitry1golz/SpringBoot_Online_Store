package com.golzstore.springstore;

import com.golzstore.springstore.services.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringStoreApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(SpringStoreApplication.class, args);
        var service = context.getBean(UserService.class);

    }

}