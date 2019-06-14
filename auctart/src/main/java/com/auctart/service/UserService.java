package com.auctart.service;

import com.auctart.domain.Roles;
import com.auctart.domain.User;
import com.auctart.repository.UserRepository;
import com.auctart.web.dto.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder encoder;

    public UserService(UserRepository repository){
        this.repository = repository;
        this.encoder = new BCryptPasswordEncoder();
    }

    public User save(UserDto userDto) {

        User user = new User(userDto.username, encoder.encode(userDto.password), userDto.email, Roles.USER);
        return repository.save(user);
    }

    public User getByUsername(String username) {

        return repository.findByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException(""));
    }
}
