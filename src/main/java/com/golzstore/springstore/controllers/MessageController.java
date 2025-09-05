package com.golzstore.springstore.controllers;

import com.golzstore.springstore.entities.Message;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {
    @RequestMapping("/hello")
    public Message sayHello() {
        return new Message("Hello0000000000000 World");
    }
}
