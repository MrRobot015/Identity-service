package com.learningspringboot.springbootapp003.auth.request_models;

import lombok.Data;

@Data
public class UserSigningModel {
    
    private String email;
    private String password;
}
