package com.learningspringboot.springbootapp003.user;

import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    
    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    
    public UserDTO createUser (UserDTO newUser){
    
        String newUserEmail = newUser.getEmail();
        String newUserPassword = newUser.getPassword();
        
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(newUser , userEntity);
        // encode the password
        userEntity.setEncryptedPassword(passwordEncoder.encode(newUserPassword));
        
        if (userRepository.existsByEmail(newUserEmail)){
            throw new RuntimeException("email already exists");
        }else {
            UserDTO createdUserDto = new UserDTO();
            UserEntity createdUser = userRepository.save(userEntity);
            BeanUtils.copyProperties(createdUser,createdUserDto);
            return createdUserDto;
        }
        
    }
    
}
