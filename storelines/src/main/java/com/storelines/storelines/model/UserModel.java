package com.storelines.storelines.model;

import com.storelines.storelines.util.BaseResponseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "users")
public class UserModel implements BaseResponseEntity, UserDetails {
    @Id
    private String id;

    @Field(name = "fname")
    private String fname;

    @Field(name = "lname")
    private String lname;

    @Indexed(unique = true)
    @Field(name = "email")
    private String email;

    @Field(name = "password")
    private String password;

    @Field(name = "salt")
    private String salt;

    @Field(name = "otp")
    private String otp;

    @Field(name = "isActive")
    private boolean isActive;

    @Field(name = "failedAttempts")
    private int failedAttempts = 0;

    @Field(name = "otpFailedAttempts")
    private int otpFailedAttempts = 0;

    @Field(name = "createdAt")
    private LocalDateTime createdAt;

    @Field(name = "updatedAt")
    private LocalDateTime updatedAt;

    @Field(name = "otpCreatedAt")
    private LocalDateTime otpCreatedAt;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
