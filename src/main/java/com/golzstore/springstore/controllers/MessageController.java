package com.golzstore.springstore.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Messages")
public class MessageController {
    @RequestMapping("/hello")
    public Message sayHello() {
        return new Message("Hello0000000000000 World");
    }
}
