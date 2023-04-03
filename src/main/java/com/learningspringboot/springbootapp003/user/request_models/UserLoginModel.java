package com.learningspringboot.springbootapp003.user.request_models;

import lombok.Data;

@Data
public class UserLoginModel {
    
    private String email;
    private String password;
}
