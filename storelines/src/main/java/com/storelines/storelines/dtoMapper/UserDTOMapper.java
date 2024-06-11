package com.storelines.storelines.dtoMapper;

import com.storelines.storelines.dto.UserDTO;
import com.storelines.storelines.model.UserModel;

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
