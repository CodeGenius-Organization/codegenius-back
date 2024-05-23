package com.codegenius.user.domain.service;

import com.codegenius.user.domain.model.UserModel;
import com.codegenius.user.domain.repository.UserRepository;
import com.codegenius.user.infra.exception.GlobalExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthentificationServiceImpl implements AuthentificationService {
    @Autowired
    private UserRepository repository;
    private PasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserModel user = repository.findByEmailAndActiveTrue(email)
                .orElseThrow(() -> new GlobalExceptionHandler.NotFoundException("User not found"));

        if (user.getActive().equals(true)){
            return user;
        } else {
            throw new GlobalExceptionHandler.NotFoundException("User not found");
        }
    }
}
