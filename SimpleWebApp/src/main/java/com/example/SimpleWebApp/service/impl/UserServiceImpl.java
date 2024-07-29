package com.example.SimpleWebApp.service.impl;

import com.example.SimpleWebApp.entities.User;
import com.example.SimpleWebApp.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {

    private InMemoryUserDetailsManager inMemoryUserDetailsManager;

    @Autowired
    public UserServiceImpl(InMemoryUserDetailsManager inMemoryUserDetailsManager) {
        this.inMemoryUserDetailsManager = inMemoryUserDetailsManager;
    }

    @Override
    public void save(User user) {
        try {
            UserDetails userDetails = org.springframework.security.core.userdetails.User.withUsername(user.getUsername())
                    .password("{noop}" + user.getPassword())
                    .build();
            inMemoryUserDetailsManager.createUser(userDetails);
            //userHelper.save(user); db code.
        } catch (Exception exception){
            throw new RuntimeException("failed to save user");
        }
    }

    public UserDetails findByUsername(String username) {
        try{
            return inMemoryUserDetailsManager.loadUserByUsername(username);
        } catch (Exception exception){
            return null;
        }
    }
}
