package com.imi.user_service.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public @Data class UserDTO {
    @Pattern(regexp = "^[A-Z]{1}[a-zA-Z\\s]{2,}$", message = "User firstName is Not Valid")
    public String firstName;

    @Pattern(regexp = "^[A-Za-z\\s]+", message = "Last name is Invalid")
    public String lastName;

    @Email
    private String email;

    @NotEmpty(message = "Address can not be Empty")
    private String address;

    @NotEmpty(message = "Password can not be Empty")
    private String password;
}
