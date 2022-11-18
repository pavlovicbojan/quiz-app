package com.pavlovic.appquiz.service;

import com.pavlovic.appquiz.model.User;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<User> findOne(Long id);

    List<User> findAll();

    Page<User> findAll(int brojStranice);

    User save(User user);

    void delete(Long id);

    Optional<User> findbyUsername(String username);

    Optional<User> findByEmail(String email);

    Page<User> getAll(int pageNo);

    Optional<User> findOneById(Long id);

}
