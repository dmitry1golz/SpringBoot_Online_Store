package com.golzstore.springstore;

import com.golzstore.springstore.entities.Address;
import com.golzstore.springstore.entities.Profile;
import com.golzstore.springstore.entities.User;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringStoreApplication {

    public static void main(String[] args) {
//        ApplicationContext context = SpringApplication.run(SpringStoreApplication.class, args);
        var user = User.builder().name("Dmitry").email("email").password("password").build();

        var address = Address.builder().street("123 Main St").city("Main St").state("Main State").zip("12345").build();

        var profile = Profile.builder().bio("bio").build();
        user.setProfile(profile);
        profile.setUser(user);

        System.out.println(user);

    }

}

