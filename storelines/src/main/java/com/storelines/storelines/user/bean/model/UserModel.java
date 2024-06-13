package com.storelines.storelines.user.bean.model;

import com.storelines.storelines.util.BaseResponseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "users")
public class UserModel implements BaseResponseEntity {
    @Id
    private String id;

    @Field(name = "fname")
    private String fname;

    @Field(name = "lname")
    private String lname;

    @Indexed(unique = true)
    @Field(name = "email")
    private String email;

    @Field(name = "role")
    private String role;

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
}
