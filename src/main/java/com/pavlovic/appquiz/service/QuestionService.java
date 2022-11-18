package com.pavlovic.appquiz.service;

import com.pavlovic.appquiz.model.Question;

import java.util.List;
import java.util.Optional;

public interface QuestionService {

    List<Question> findAllByTopicId(Long topicId);

    Optional<Question> findOneById(Long id);
}
