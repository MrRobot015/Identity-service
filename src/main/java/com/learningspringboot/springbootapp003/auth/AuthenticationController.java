package com.learningspringboot.springbootapp003.auth;

import com.learningspringboot.springbootapp003.auth.request_models.RegisterRequestModel;
import com.learningspringboot.springbootapp003.auth.request_models.UserSigningModel;
import com.learningspringboot.springbootapp003.auth.response_models.AuthenticationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    
    private final AuthenticationService authenticationService;
    
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> registerUser(@RequestBody RegisterRequestModel requestData){
  
        AuthenticationResponse response = authenticationService.register(requestData);
        
        return ResponseEntity.ok(response);
    }
    
    
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticateUser(@RequestBody UserSigningModel requestData){
    
        AuthenticationResponse response = authenticationService.authenticate(requestData);
        
        return ResponseEntity.ok(response);
    }
}
