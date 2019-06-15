package com.auctart.web;

import com.auctart.domain.User;
import com.auctart.service.UserService;
import com.auctart.web.dto.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;


@Controller
@RequestMapping("api/user")
public class UserController {
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping(value = "/register")
    public ResponseEntity save(@RequestBody @Valid UserDto userDto) {
        return ResponseEntity.ok(this.service.save(userDto));
    }

    @GetMapping(value = "/{username}")
    public ResponseEntity<User> getByUserName(@PathVariable("username") String username) {
        User user = this.service.getByUsername(username);
        return ResponseEntity.ok(user);
    }

    @GetMapping(value = "/current")
    public ResponseEntity<User> getCurrent(Authentication authentication) {
        return ResponseEntity.ok(
                ((HashMap<String, User>) authentication.getDetails()).get("account")
        );
    }
}
