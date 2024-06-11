package com.storelines.storelines.user.service;

import com.storelines.storelines.exception.DAOException;
import com.storelines.storelines.exception.ServiceException;
import com.storelines.storelines.user.bean.model.UserModel;
import com.storelines.storelines.user.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Objects;
import java.util.Random;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDAO userDAO;

//    @Autowired
//    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserModel saveUser(UserModel userModel) throws ServiceException {
        try {
            String salt = generateSalt();
            String hashPass = hashPassword(userModel.getPassword(), salt);
            userModel.setActive(false);
            userModel.setPassword(hashPass);
            userModel.setSalt(salt);
            userModel.setOtp(generateOtp());
            userModel.setOtpCreatedAt(LocalDateTime.now());

            return userDAO.saveUser(userModel);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public UserModel editUser(UserModel userModel) throws ServiceException {
        try {
            return userDAO.editUser(userModel);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public UserModel getUserByEmail(String email) throws ServiceException {
        try {
            return userDAO.getUserByEmail(email);
        } catch (DAOException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public boolean checkPassword(String hashPassword, String salt, String inputPassword) {
        return Objects.equals(hashPassword, hashPassword(inputPassword, salt));
    }

    @Override
    public String generateOtp() {
        Random random = new Random();
        int min = 0;
        int max = 999999;

        int randomNumber = random.nextInt(max - min + 1) + min;
        String otp = String.format("%06d", randomNumber);

        return otp;
    }

    private String generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] saltBytes = new byte[16];
        random.nextBytes(saltBytes);
        return Base64.getEncoder().encodeToString(saltBytes);
    }

    private String hashPassword(String password, String salt) {

//        return passwordEncoder.encode(password + salt);
        return password + salt;
    }
}
