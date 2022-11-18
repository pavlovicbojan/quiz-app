package com.pavlovic.appquiz.model;

import com.pavlovic.appquiz.web.dto.UserDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PaginationUsers {

    private List<UserDto> users;

    private int totalPages;
}
