package com.learningspringboot.springbootapp003.user;


import com.learningspringboot.springbootapp003.auth.response_models.AuthenticationResponse;
import com.learningspringboot.springbootapp003.user.response_models.UserResponseModel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

   private final UserService userService;
    
    
    
    @GetMapping
    public ResponseEntity<List<UserResponseModel>> getAllUsers(){
        
        var response = userService.getAllUsers();
        
        return ResponseEntity.ok(response);
        
    }
    
    
}
