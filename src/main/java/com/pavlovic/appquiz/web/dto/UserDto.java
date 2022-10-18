package com.pavlovic.appquiz.web.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class UserDto {

//    @Positive(message = "Id mora biti pozitivan broj.")
//    private Long id;

    @NonNull
    private String username;

    private String eMail;

    private String name;

    private String lastname;

}

