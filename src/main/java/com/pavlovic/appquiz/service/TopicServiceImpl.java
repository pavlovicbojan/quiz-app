package com.pavlovic.appquiz.service;

import com.pavlovic.appquiz.model.Topic;
import com.pavlovic.appquiz.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TopicServiceImpl implements TopicService{

    @Autowired
    private TopicRepository topicRepository;

    @Override
    public List<Topic> findAll() {
        return topicRepository.findAll();
    }

    @Override
    public Optional<Topic> findOneById(Long id) {
        return topicRepository.findById(id);
    }


}
