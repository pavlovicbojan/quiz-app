package com.pavlovic.appquiz.repository;

import com.pavlovic.appquiz.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findFirstByUsername(String username);

    Optional<User> findFirstByUsernameAndPassword(String username, String password);

    Optional<User> findFirstByeMail(String email);

    Page<User> findAll(Pageable pageable);

    Optional<User> findOneById(Long id);
}

