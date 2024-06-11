package com.storelines.storelines.user.dao;

import com.storelines.storelines.exception.DAOException;
import com.storelines.storelines.user.bean.model.UserModel;

import com.storelines.storelines.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserDAOImpl implements UserDAO{
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserModel saveUser(UserModel userModel) throws DAOException {
        try {
            return userRepository.save(userModel);
        } catch (Exception e){
            throw new DAOException(e.getMessage(),e);
        }
    }

    @Override
    public UserModel getUserByEmail(String email) throws DAOException {
        try {
            return userRepository.findByEmail(email).orElse(null);
        } catch (Exception e){
            throw new DAOException(e.getMessage(),e);
        }
    }

    @Override
    public UserModel editUser(UserModel userModel) throws DAOException {
        try {
            return userRepository.save(userModel);
        } catch (Exception e){
            throw new DAOException(e.getMessage(),e);
        }
    }
}
