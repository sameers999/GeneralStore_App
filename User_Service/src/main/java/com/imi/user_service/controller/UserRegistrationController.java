package com.imi.user_service.controller;

import com.imi.user_service.dto.ResponseDTO;
import com.imi.user_service.dto.UserDTO;
import com.imi.user_service.dto.UserLoginDTO;
import com.imi.user_service.model.UserRegistration;
import com.imi.user_service.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserRegistrationController {

    //Ability to Create Account
    @Autowired
    IUserService userRegistrationService;

    @PostMapping("/register")
    public ResponseEntity<ResponseDTO> addUser(@Valid @RequestBody UserDTO userDTO) {
        String newUser = userRegistrationService.addUser(userDTO);
        ResponseDTO responseDTO = new ResponseDTO("User Registered Successfully", newUser);
        return new ResponseEntity(responseDTO, HttpStatus.CREATED);
    }

    //Ability to Login
    @GetMapping("/login")
    public ResponseEntity<ResponseDTO> userLogin(@RequestBody UserLoginDTO userLoginDTO) {
        System.out.println("Entered......");
        return new ResponseEntity<ResponseDTO>(userRegistrationService.loginUser(userLoginDTO),HttpStatus.OK);
    }

    //Ability to get all users
    @GetMapping(value = "/getAll")
    public ResponseEntity<String> getAllUsers() {
        List<UserRegistration> listofUsers = userRegistrationService.getAllUsers();
        ResponseDTO dto = new ResponseDTO("User retrived successfully (:",listofUsers);
        return new ResponseEntity(dto,HttpStatus.OK);
    }

    //    Ability to getAll users by token
    @GetMapping(value = "/getAll/{token}")
    public ResponseEntity<ResponseDTO> getAllUserDataByToken(@PathVariable String token) {
        List<UserRegistration> listofUsers = userRegistrationService.getAllUserDataByToken(token);
        ResponseDTO dto = new ResponseDTO("Data retieved successfully(:",listofUsers);
        return new ResponseEntity(dto, HttpStatus.OK);
    }

    @GetMapping("/findById/{userId}")
    public UserRegistration getByIdAPI(@PathVariable Integer userId){
        UserRegistration user =  userRegistrationService.getByIdAPI(userId);
        return user;
    }
}