package com.pavlovic.appquiz.service;

import com.pavlovic.appquiz.model.User;
import com.pavlovic.appquiz.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Optional<User> findOne(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Page<User> findAll(int brojStranice) {
        return userRepository.findAll(PageRequest.of(brojStranice,10));
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Optional<User> findbyUsername(String username) {
        return userRepository.findFirstByUsername(username);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        Optional<User> user = userRepository.findFirstByeMail(email);
        return user;
    }

    @Override
    public Page<User> getAll(int pageNo) {
        return userRepository.findAll(PageRequest.of(pageNo,1));
    }

    @Override
    public Optional<User> findOneById(Long id) {
        return userRepository.findOneById(id);

    }
}
