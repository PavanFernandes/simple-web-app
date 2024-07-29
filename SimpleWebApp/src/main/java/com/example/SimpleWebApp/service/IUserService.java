package com.example.SimpleWebApp.service;

import com.example.SimpleWebApp.entities.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface IUserService {

    void save(User user);

    UserDetails findByUsername(String username);
}
