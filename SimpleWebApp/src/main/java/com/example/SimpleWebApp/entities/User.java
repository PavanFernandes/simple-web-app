package com.example.SimpleWebApp.entities;

import lombok.Data;

//@Entity
//@Table(name = "users")
@Data
public class User {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id")
    private Long id;

//  @Column(name = "username")
    private String username;

//  @Column(name = "password")
    private String password;

//  @Column(name = "enabled")
    private int enabled;

//  @Column(name = "authorities")
    private String authorities;
}
