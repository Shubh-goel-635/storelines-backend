package com.storelines.storelines.user.bean;

import com.storelines.storelines.user.bean.dto.UserDTO;
import com.storelines.storelines.user.bean.model.UserModel;

import java.time.LocalDateTime;

public class UserDTOMapper {
    public UserDTOMapper() {}

    public static UserModel convert(UserDTO userDTO){
        UserModel userModel = new UserModel();

        userModel.setFname(userDTO.getFname());
        userModel.setLname(userDTO.getLname());
        userModel.setEmail(userDTO.getEmail());
        userModel.setCreatedAt(LocalDateTime.now());
        userModel.setUpdatedAt(LocalDateTime.now());

        return userModel;
    }
}
