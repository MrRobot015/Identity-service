package com.learningspringboot.springbootapp003.user.response_models;

import com.learningspringboot.springbootapp003.user.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseModel {
    
    private String userId;
    private String firstName;
    private String  lastName;
    private String email;
    private Role role;
}
