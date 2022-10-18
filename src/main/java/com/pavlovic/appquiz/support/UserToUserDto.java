package com.pavlovic.appquiz.support;

import com.pavlovic.appquiz.model.User;
import com.pavlovic.appquiz.web.dto.UserDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserToUserDto implements Converter<User, UserDto> {

    @Override
    public UserDto convert(User source) {
        return null;
    }
}
