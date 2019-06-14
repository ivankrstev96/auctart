package com.auctart.web;

import com.auctart.domain.User;
import com.auctart.service.UserService;
import com.auctart.web.dto.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;


@Controller
@RequestMapping("api/user")
public class UserController {
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping(value = "/register")
    public ResponseEntity save (@RequestBody @Valid UserDto userDto){
        return ResponseEntity.ok(this.service.save(userDto));
    }
}
