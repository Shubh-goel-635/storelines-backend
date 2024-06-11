package com.storelines.storelines.user.service;

import com.storelines.storelines.exception.ServiceException;
import com.storelines.storelines.user.bean.model.UserModel;

public interface UserService {
    UserModel saveUser(UserModel userModel) throws ServiceException;
    UserModel editUser(UserModel userModel) throws ServiceException;
    UserModel getUserByEmail(String email) throws ServiceException;
    String generateOtp();
    boolean checkPassword(String hashPassword, String salt, String inputPassword);

}
