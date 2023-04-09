package com.learningspringboot.springbootapp003.user;

import com.learningspringboot.springbootapp003.user.response_models.UserResponseModel;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    
    
    public List<UserResponseModel> getAllUsers() {
    
        List<UserEntity> unformattedUserData = userRepository.findAll();
    
        
        
        List<UserResponseModel> formattedUserData =  new ArrayList<>();
        
        unformattedUserData.forEach(userEntity -> {
             UserResponseModel userResponseModel = new UserResponseModel();
             BeanUtils.copyProperties(userEntity,userResponseModel);
             formattedUserData.add(userResponseModel);
        });
        
        
        return formattedUserData;
    }
    
}
