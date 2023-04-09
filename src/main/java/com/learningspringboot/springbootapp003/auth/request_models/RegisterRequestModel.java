package com.learningspringboot.springbootapp003.auth.request_models;

import lombok.Data;

@Data
public class RegisterRequestModel {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
