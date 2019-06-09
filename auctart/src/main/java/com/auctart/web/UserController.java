package com.auctart.web;

import com.auctart.service.UserService;
import org.springframework.web.bind.annotation.RestController;

@RestController("api/user")
public class UserController {
    UserService service;

    UserController(UserService service){
        this.service = service;
    }
}
