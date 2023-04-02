package com.learningspringboot.springbootapp003.user;

import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Data
public class UserDTO implements Serializable {
    private long _id;
    private UUID userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String encryptedPassword;
    private String emailVerificationToken;
    private Boolean isEmailVerified = false;
}
