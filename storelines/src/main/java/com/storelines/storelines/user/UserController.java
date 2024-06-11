package com.storelines.storelines.user;

import com.storelines.storelines.user.bean.dto.UserDTO;
import com.storelines.storelines.user.bean.UserDTOMapper;
import com.storelines.storelines.exception.ServiceException;
import com.storelines.storelines.user.bean.model.UserModel;
import com.storelines.storelines.variant.service.UserService;
import com.storelines.storelines.util.ResponseEnvelope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

@RestController("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/rejisteruser")
    public ResponseEntity<ResponseEnvelope> saveUser(UserDTO userDTO) {
        UserModel userModel = UserDTOMapper.convert(userDTO);

        try {
            UserModel checkEistingUser = userService.getUserByEmail(userModel.getEmail());
            if (checkEistingUser == null) {
                userService.saveUser(userModel);
                return new ResponseEntity<>(new ResponseEnvelope(HttpStatus.OK.value()), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new ResponseEnvelope(HttpStatus.CONFLICT.value(), "Email already exists."), HttpStatus.OK);
            }
        } catch (ServiceException e) {
            return new ResponseEntity<>(new ResponseEnvelope(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseEnvelope> login(@RequestBody UserDTO userDTO) {
        UserModel userModel = UserDTOMapper.convert(userDTO);
        try {
            UserModel outputUserModel = userService.getUserByEmail(userModel.getEmail());
            if (outputUserModel == null) {
                return new ResponseEntity<>(new ResponseEnvelope(HttpStatus.NOT_FOUND.value(), "Email not exists"), HttpStatus.OK);
            } else if (!outputUserModel.isActive() || outputUserModel.getFailedAttempts() == 3){
                userModel.setOtp(userService.generateOtp());
                userModel.setOtpFailedAttempts(0);
                userModel.setOtpCreatedAt(LocalDateTime.now());
                userModel.setActive(false);
                userService.editUser(userModel);
                return new ResponseEntity<>(new ResponseEnvelope(HttpStatus.FORBIDDEN.value(), "Account not activated"), HttpStatus.OK);
            } else if (userService.checkPassword(outputUserModel.getPassword(), outputUserModel.getSalt(), userDTO.getPassword()) && userModel.getFailedAttempts() < 3) {
                userModel.setFailedAttempts(0);
                userService.editUser(userModel);
                return new ResponseEntity<>(new ResponseEnvelope(HttpStatus.OK.value(), "Login successful"), HttpStatus.OK);
            } else {
                userModel.setFailedAttempts(userModel.getFailedAttempts() + 1);
                userService.editUser(userModel);
                return new ResponseEntity<>(new ResponseEnvelope(HttpStatus.UNAUTHORIZED.value(), "Incorrect password"), HttpStatus.OK);
            }
        } catch (ServiceException e) {
            return new ResponseEntity<>(new ResponseEnvelope(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/validateotp")
    public ResponseEntity<ResponseEnvelope> activate(@RequestParam String email, @RequestParam String otp) {
        try {
            UserModel userModel = userService.getUserByEmail(email);
            LocalDateTime otpCreatedTime = userModel.getOtpCreatedAt();
            Duration duration = Duration.between(otpCreatedTime, LocalDateTime.now());
            if (Objects.equals(otp, userModel.getOtp()) && duration.toMinutes() < 10 && userModel.getOtpFailedAttempts() < 3) {
                userModel.setActive(true);
                userModel.setFailedAttempts(0);
                userModel.setOtpFailedAttempts(0);
                userService.editUser(userModel);
                return new ResponseEntity<>(new ResponseEnvelope(HttpStatus.OK.value()), HttpStatus.OK);
            } else if (duration.toMinutes() > 10 || userModel.getOtpFailedAttempts() > 3) {
                userModel.setOtpFailedAttempts(0);
                userModel.setOtp(userService.generateOtp());
                userModel.setOtpCreatedAt(LocalDateTime.now());
                return new ResponseEntity<>(new ResponseEnvelope(HttpStatus.GONE.value(), "OTP expired, new otp sent to email"), HttpStatus.OK);
            } else {
                userModel.setOtpFailedAttempts(userModel.getOtpFailedAttempts() + 1);
                userService.editUser(userModel);
                return new ResponseEntity<>(new ResponseEnvelope(HttpStatus.BAD_REQUEST.value(), "OTP doesn't match"), HttpStatus.OK);
            }
        } catch (ServiceException e) {
            return new ResponseEntity<>(new ResponseEnvelope(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/forgotpassword")
    public ResponseEntity<ResponseEnvelope> forgotpassword(@RequestBody UserDTO userDTO) {
        try {
            UserModel userModel = UserDTOMapper.convert(userDTO);
            UserModel existingUserModel = userService.getUserByEmail(userModel.getEmail());

            if (!existingUserModel.isActive()) {
                existingUserModel.setOtpFailedAttempts(0);
                existingUserModel.setOtp(userService.generateOtp());
                existingUserModel.setOtpCreatedAt(LocalDateTime.now());
                userService.editUser(existingUserModel);
                return new ResponseEntity<>(new ResponseEnvelope(HttpStatus.FORBIDDEN.value(), "Account not activated"), HttpStatus.OK);
            } else {
                existingUserModel.setPassword(userDTO.getPassword());
                existingUserModel.setActive(false);
                existingUserModel.setFailedAttempts(0);
                existingUserModel.setOtp(userService.generateOtp());
                existingUserModel.setOtpCreatedAt(LocalDateTime.now());
                existingUserModel.setOtpFailedAttempts(0);
                userService.editUser(existingUserModel);
                return new ResponseEntity<>(new ResponseEnvelope(HttpStatus.OK.value()), HttpStatus.OK);
            }
        } catch (ServiceException e) {
            return new ResponseEntity<>(new ResponseEnvelope(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
