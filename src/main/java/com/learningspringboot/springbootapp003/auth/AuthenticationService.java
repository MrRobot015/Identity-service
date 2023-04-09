package com.learningspringboot.springbootapp003.auth;

import com.learningspringboot.springbootapp003.auth.request_models.RegisterRequestModel;
import com.learningspringboot.springbootapp003.auth.request_models.UserSigningModel;
import com.learningspringboot.springbootapp003.auth.response_models.AuthenticationResponse;
import com.learningspringboot.springbootapp003.config.JwtService;
import com.learningspringboot.springbootapp003.user.Role;
import com.learningspringboot.springbootapp003.user.UserEntity;
import com.learningspringboot.springbootapp003.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    
    // --------------------------------------------------------------
    public AuthenticationResponse register(RegisterRequestModel newUser){
        
        var user = UserEntity.builder()
                .firstName(newUser.getFirstName())
                .lastName(newUser.getLastName())
                .email(newUser.getEmail())
                .encryptedPassword(passwordEncoder.encode(newUser.getPassword()))
                .role(Role.USER)
                .build();
        
        UserEntity createdUser = userRepository.save(user);
        String token = jwtService.generateToken(createdUser);
        
        return AuthenticationResponse.builder()
                .userId(createdUser.getUserId())
                .token(token)
                .build();
    }
    
    // --------------------------------------------------------------
    public AuthenticationResponse authenticate(UserSigningModel userAuthData) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                       userAuthData.getEmail(),
                       userAuthData.getPassword()
                )
        );
        
        var user = userRepository.findByEmail(userAuthData.getEmail())
                .orElseThrow(()-> new UsernameNotFoundException("invalid email"));
        String token = jwtService.generateToken(user);
    
        return AuthenticationResponse.builder()
                .userId(user.getUserId())
                .token(token)
                .build();
    
    }
    
}
