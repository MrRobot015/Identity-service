package com.learningspringboot.springbootapp003.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {
   
    public Optional<UserEntity> findByEmail(String email);
    
}
