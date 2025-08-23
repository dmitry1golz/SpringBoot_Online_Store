package com.golzstore.springstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringStoreApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(SpringStoreApplication.class, args);
        var userService = context.getBean(UserService.class);
        userService.registerUser(new User(1L, "dmitryGolz@gmail.com", "Dmitry Golz", "12345678" ));

    }

}

