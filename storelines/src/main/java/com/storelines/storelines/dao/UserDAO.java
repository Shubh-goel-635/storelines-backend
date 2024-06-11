package com.storelines.storelines.dao;

import com.storelines.storelines.exception.DAOException;
import com.storelines.storelines.model.UserModel;

public interface UserDAO {
    UserModel saveUser(UserModel userModel) throws DAOException;
    UserModel getUserByEmail(String email) throws DAOException;
    UserModel editUser(UserModel userModel) throws DAOException;

}
