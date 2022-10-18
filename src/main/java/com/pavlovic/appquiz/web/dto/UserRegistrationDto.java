package com.pavlovic.appquiz.web.dto;

import com.pavlovic.appquiz.web.dto.UserDto;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Setter
@Getter
public class UserRegistrationDto extends UserDto {

//    @NotBlank(message = "Lozinka nije zadata.")
    @NonNull
    private String password;

//    @NotBlank(message = "Ponovljena lozinka nije zadata.")
    @NonNull
    private String repeatedPassword;
}

