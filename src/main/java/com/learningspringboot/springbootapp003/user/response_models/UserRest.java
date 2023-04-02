package com.learningspringboot.springbootapp003.user.response_models;

import lombok.Data;

import java.util.UUID;

@Data
public class UserRest {
    private UUID userId;
    private String firstName;
    private String lastName;
    private String email;
}
