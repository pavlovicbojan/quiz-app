package com.pavlovic.appquiz.service;

import com.pavlovic.appquiz.model.Question;
import com.pavlovic.appquiz.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionServiceImpl implements QuestionService{

    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public List<Question> findAllByTopicId(Long topicId) {
        return questionRepository.findAllByTopicId(topicId);
    }

    @Override
    public Optional<Question> findOneById(Long id) {
        Optional<Question> question = questionRepository.findById(id);
        return question;
    }
}
