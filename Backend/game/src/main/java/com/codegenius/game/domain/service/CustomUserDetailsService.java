package com.codegenius.game.domain.service;

import com.codegenius.game.domain.model.UserModel;
import com.codegenius.game.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserModel user = repository.findByEmailAndActiveTrue(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        if (user.getActive().equals(true)) {
            return new User(user.getEmail(), user.getPassword(), user.getAuthorities());
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }
}
