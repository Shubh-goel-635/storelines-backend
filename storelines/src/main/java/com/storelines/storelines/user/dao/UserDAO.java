package com.storelines.storelines.user.dao;

import com.storelines.storelines.exception.DAOException;
import com.storelines.storelines.user.bean.model.UserModel;

public interface UserDAO {
    UserModel saveUser(UserModel userModel) throws DAOException;
    UserModel getUserByEmail(String email) throws DAOException;
    UserModel editUser(UserModel userModel) throws DAOException;

}
