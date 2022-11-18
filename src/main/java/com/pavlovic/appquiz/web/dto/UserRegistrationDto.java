package com.pavlovic.appquiz.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserRegistrationDto extends UserDto {

    @NonNull
    private String password;

    @NonNull
    private String repeatedPassword;
}

