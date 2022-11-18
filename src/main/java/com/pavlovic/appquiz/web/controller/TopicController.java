package com.pavlovic.appquiz.web.controller;

import com.pavlovic.appquiz.service.TopicService;
import com.pavlovic.appquiz.support.TopicToTopicDto;
import com.pavlovic.appquiz.web.dto.TopicDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/topics")
public class TopicController {

    @Autowired
    private TopicService topicService;

    @Autowired
    private TopicToTopicDto topicDto;

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping
    public ResponseEntity<List<TopicDto>> getAllTopics() {

        List topics = topicService.findAll();

        List<TopicDto> topicsDto = topicDto.convert(topics);

        return ResponseEntity.ok().body(topicsDto);
    }
}
