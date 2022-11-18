package com.pavlovic.appquiz.support;

import com.pavlovic.appquiz.model.Role;
import com.pavlovic.appquiz.model.User;
import com.pavlovic.appquiz.web.dto.UserRegistrationDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserRegistrationDtoToUser implements Converter<UserRegistrationDto, User> {

    @Override
    public User convert(UserRegistrationDto source) {
        User user = new User();
        user.setUsername(source.getUsername());
        user.setPassword(source.getPassword());
        user.setRole(Role.USER);
        user.setEMail(source.getEmail());
        user.setFirstName(source.getName());
        user.setLastName(source.getLastname());
        return user;
    }
}
