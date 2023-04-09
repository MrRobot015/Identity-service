package com.learningspringboot.springbootapp003.user;

import com.learningspringboot.springbootapp003.utils.StringPrefixedSequenceIdGenerator;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_user")
public class UserEntity implements UserDetails {
    
    @Id
    @SequenceGenerator(name = "id_seq", sequenceName = "id_seq", initialValue = 1, allocationSize = 2)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_seq")
    @Column(unique = true)
    private long _id;
    
    
    @GenericGenerator(
            name = "user_seq",
            strategy = "com.learningspringboot.springbootapp003.utils.StringPrefixedSequenceIdGenerator",
            parameters = {
                    @Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "50"),
                    @Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "B_"),
                    @Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d")})
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @Column(name = "user_id", unique = true)
    private String userId;
    
    
    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;
    
    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;
    
    @Column(nullable = false, length = 120, unique = true)
    private String email;
    
    @Enumerated(EnumType.STRING)
    private Role role;
    private String encryptedPassword;
    
    
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
        
        return true;
    }
    
}

