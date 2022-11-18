package com.pavlovic.appquiz.service;

import com.pavlovic.appquiz.model.Topic;

import java.util.List;
import java.util.Optional;

public interface TopicService {

    List<Topic> findAll();

    Optional<Topic> findOneById(Long id);

}
