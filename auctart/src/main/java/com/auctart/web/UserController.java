package com.auctart.web;

import com.auctart.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("api/user")
public class UserController {
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }
}
