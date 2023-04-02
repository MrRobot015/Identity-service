package com.learningspringboot.springbootapp003.user;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.lang.NonNull;

import java.util.UUID;

@Entity
@Table(name = "user_table")
@Data
public class UserEntity {
    
    @Id
    @SequenceGenerator(name = "id_seq",sequenceName = "id_seq",initialValue = 1 ,allocationSize = 2)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "id_seq")
    @Column(unique = true ,nullable = false)
    private long _id;
    
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "user_id" ,unique = true , nullable = false)
    private UUID userId;
    
    @Column(name = "first_name" , nullable = false ,length = 50)
    private String firstName;
    
    @Column(name = "last_name" , nullable = false ,length = 50)
    private String lastName;
    
    @Column(nullable = false ,length = 120, unique = true)
    private String email;
    
    @Column
    private String encryptedPassword;
    
    private String emailVerificationToken;
    
    @Column
    private Boolean isEmailVerified = false;
}
