package com.learningspringboot.springbootapp003.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_table")
public class UserEntity implements UserDetails {
    
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
    
    @Enumerated(EnumType.STRING)
    private Role role;
    private String encryptedPassword;
    
    private String emailVerificationToken;
    
    @Column(nullable = false)
    private Boolean isEmailVerified = false;
    
    // -------------------------------------------------------
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        
        return List.of(new SimpleGrantedAuthority(role.name()));
    }
    // -------------------------------------------------------
    @Override
    public String getPassword() {
        
        return encryptedPassword;
    }
    // -------------------------------------------------------
    @Override
    public String getUsername() {
        
        return email;
    }
    // -------------------------------------------------------
    @Override
    public boolean isAccountNonExpired() {
        
        return true;
    }
    // -------------------------------------------------------
    @Override
    public boolean isAccountNonLocked() {
        
        return true;
    }
    // -------------------------------------------------------
    @Override
    public boolean isCredentialsNonExpired() {
        
        return true;
    }
    // -------------------------------------------------------
    @Override
    public boolean isEnabled() {
        
        return isEmailVerified;
    }
    
}

