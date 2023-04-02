package com.learningspringboot.springbootapp003.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {
    public UserEntity findByUserId(UUID userId);
    
    public UserEntity deleteByUserId(UUID userId);
    
    public Boolean existsByUserId(UUID userId);
    
    public Boolean existsByEmail(String email);
    
}
