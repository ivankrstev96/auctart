package com.auctart.web;

import com.auctart.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("api/user")
public class UserController {
    UserService service;

    UserController(UserService service){
        this.service = service;
    }
}
