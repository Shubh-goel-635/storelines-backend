package com.storelines.storelines.service;

import com.storelines.storelines.exception.DAOException;
import com.storelines.storelines.exception.ServiceException;
import com.storelines.storelines.model.UserModel;

public interface UserService {
    UserModel saveUser(UserModel userModel) throws ServiceException;
    UserModel editUser(UserModel userModel) throws ServiceException;
    UserModel getUserByEmail(String email) throws ServiceException;
    String generateOtp();
    boolean checkPassword(String hashPassword, String salt, String inputPassword);

}
