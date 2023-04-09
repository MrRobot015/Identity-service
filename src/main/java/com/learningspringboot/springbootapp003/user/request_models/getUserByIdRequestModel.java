package com.learningspringboot.springbootapp003.user.request_models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class getUserByIdRequestModel {
    
    private String userId;
    
}
