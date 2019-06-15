package com.auctart.configuration;

import com.auctart.domain.User;
import com.auctart.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository repository;

    UserDetailsServiceImpl(UserRepository repository){
        this.repository = repository;
    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUserName(username).orElseThrow(() ->
                new UsernameNotFoundException("User with bla bla not found"));
    }
}
