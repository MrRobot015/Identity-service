package com.learningspringboot.springbootapp003.user;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.learningspringboot.springbootapp003.user.request_models.UserDetailsRequestModel;
import com.learningspringboot.springbootapp003.user.response_models.UserRest;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;
    
    public UserController(UserService userService) {
        
        this.userService = userService;
    }
    
    @GetMapping
    public List<UserRest> getAllUsers(){
        return null;
    }
    
    @PostMapping
    public UserRest createUser(@RequestBody UserDetailsRequestModel newUser){
    
        UserRest response = new UserRest();
        
        UserDTO newUserDto = new UserDTO();
        BeanUtils.copyProperties(newUser,newUserDto);
        
        UserDTO createdUser = userService.createUser(newUserDto);
        BeanUtils.copyProperties(createdUser,response);
        
        return response;
    }
}
