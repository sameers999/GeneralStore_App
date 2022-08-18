    package com.imi.user_service.service;

import com.imi.user_service.dto.ResponseDTO;
import com.imi.user_service.dto.UserDTO;
import com.imi.user_service.dto.UserLoginDTO;
import com.imi.user_service.model.UserRegistration;

import java.util.List;

public interface IUserService {
    String addUser(UserDTO userDTO);
    String verifyUser(String token);
    List<UserRegistration> getAllUsers();

    ResponseDTO loginUser(UserLoginDTO userLoginDTO);

    Object getUserByToken(String token);

    UserRegistration updateUser(String email, UserDTO userDTO);

    List<UserRegistration> getAllUserDataByToken(String token);

    UserRegistration updateRecordById(Integer id, UserDTO userDTO);

    UserRegistration getByIdAPI(Integer userId);

}


