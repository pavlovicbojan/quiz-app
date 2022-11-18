package com.pavlovic.appquiz.support;

import com.pavlovic.appquiz.model.User;
import com.pavlovic.appquiz.web.dto.UserDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserToUserDto implements Converter<User, UserDto> {

    @Override
    public UserDto convert(User source) {
        UserDto userDto = new UserDto();
        userDto.setId(source.getId());
        userDto.setName(source.getFirstName());
        userDto.setLastname(source.getLastName());
        userDto.setEmail(source.getEMail());
        userDto.setUsername(source.getUsername());
        return userDto;
    }

    public List<UserDto> convert(List<User> users){
        List<UserDto> userDtos = new ArrayList<>();

        for (User user : users) {
            userDtos.add(convert(user));
        }

        return userDtos;
    }
}
