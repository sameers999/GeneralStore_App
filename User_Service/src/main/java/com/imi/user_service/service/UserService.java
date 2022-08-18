package com.imi.user_service.service;

import com.ctc.wstx.shaded.msv_core.verifier.regexp.StringToken;
import com.imi.user_service.Util.EmailSenderService;
import com.imi.user_service.Util.TokenUtility;
import com.imi.user_service.dto.ResponseDTO;
import com.imi.user_service.dto.UserDTO;
import com.imi.user_service.dto.UserLoginDTO;
import com.imi.user_service.exception.GeneralStoreException;
import com.imi.user_service.model.UserRegistration;
import com.imi.user_service.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.GeneralSecurityException;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserService implements IUserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    EmailSenderService mailService;

    @Autowired
    TokenUtility util;
    //  Ability to Create account
    @Override
    public String addUser(UserDTO userDTO) {
        UserRegistration newUser = new UserRegistration(userDTO);
        userRepository.save(newUser);
        String token = util.createToken(newUser.getUserId());
        mailService.sendEmail(newUser.getEmail(), "Test Email", "Registerd SuccessFully,hii: "
                + newUser.getFirstName() + "Please Click here to get data-> "
                + "http://localhost:8086/user/verify/" + token);
        return token;
    }
    //Ability to verify user by token
    @Override
    public String verifyUser(String token) {
        int id = util.decodeToken(token);
        Optional<UserRegistration> user = userRepository.findById(id);

        if (user.isPresent()) {
            return user.toString();
        } else
            return null;
    }

    //    Ability to getAll users
    @Override
    public List<UserRegistration> getAllUsers() {
        List<UserRegistration> getUsers = userRepository.findAll();
        return getUsers;
    }

    //    Ability to getUser by token
    @Override
    public Object getUserByToken(String token) {
        int id = util.decodeToken(token);
        Optional<UserRegistration> getUser = userRepository.findById(id);
        if (getUser.isPresent()) {
            mailService.sendEmail("syedmahammadSameer999@gmail.com", "Test Email", "Get your data with this token, hii: "
                    + getUser.get().getEmail() + "Please Click here to get data-> "
                    + "http://localhost:8086/user/getAll/" + token);
            return getUser;

        } else {
            throw new GeneralStoreException("Record for provided userId is not found");
        }
    }

    //    Ability to login
    @Override
    public ResponseDTO loginUser(UserLoginDTO userLoginDTO) {
        ResponseDTO dto = new ResponseDTO();
        Optional<UserRegistration> login = userRepository.findByEmailid(userLoginDTO.getEmail());
        if (login.isPresent()) {
            String pass = login.get().getPassword();
            if (login.get().getPassword().equals(userLoginDTO.getPassword())) {
                dto.setMessage("login successful ");
                dto.setData(login.get());
                return dto;
            } else {
                dto.setMessage("Sorry! login is unsuccessful");
                dto.setData("Wrong password");
                return dto;
            }
        }
        return new ResponseDTO("User not found!", "Wrong email");
    }

    //    Ability to Update by id
    @Override
    public UserRegistration updateUser(String id, UserDTO userDTO) {
        Optional<UserRegistration> updateUser = userRepository.findByEmailid(id);
        if (updateUser.isPresent()) {
            UserRegistration newBook = new UserRegistration(updateUser.get().getUserId(), userDTO);
            userRepository.save(newBook);
            String token = util.createToken(newBook.getUserId());
            mailService.sendEmail(newBook.getEmail(), "Welcome " + newBook.getFirstName(), "Click here \n http://localhost:8086/user/update/" + token);
            return newBook;

        }
        throw new GeneralStoreException("Book Details for email not found");
    }

    @Override
    public List<UserRegistration> getAllUserDataByToken(String token) {
        int id = util.decodeToken(token);
        Optional<UserRegistration> isContactPresent = userRepository.findById(id);
        if (isContactPresent.isPresent()) {
            List<UserRegistration> listOfUsers = userRepository.findAll();
            mailService.sendEmail("syedmahammadSameer999@gmail.com", "Test Email", "Get your data with this token, hii: "
                    + isContactPresent.get().getEmail() + "Please Click here to get data-> "
                    + "http://localhost:8086/user/getAll/" + token);
            return listOfUsers;
        } else {
            System.out.println("Exception ...Token not found!");
            return null;
        }
    }

    @Override
    public UserRegistration updateRecordById(Integer id, UserDTO userDTO) {

        Optional<UserRegistration> addressBook = userRepository.findById(id);
        if (addressBook.isPresent()) {
            UserRegistration newBook = new UserRegistration(id, userDTO);
            userRepository.save(newBook);
            return newBook;

        }
        throw new GeneralStoreException("User Details for id not found");
    }

    @Override
    public UserRegistration getByIdAPI(Integer userId) {
        Optional<UserRegistration> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            throw new GeneralStoreException("There are no users with given id");
        }
        return user.get();

    }

}

