package com.pavlovic.appquiz.support;

import com.pavlovic.appquiz.model.Topic;
import com.pavlovic.appquiz.web.dto.TopicDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TopicToTopicDto implements Converter<Topic, TopicDto> {

    @Autowired
    private QuestionToQuestionDto toQuestionDto;


    @Override
    public TopicDto convert(Topic source) {
        TopicDto topicDto = new TopicDto();
        topicDto.setId(source.getId());
        topicDto.setName(source.getName());
        topicDto.setQuestions(toQuestionDto.convert(source.getQuestions()));
        return topicDto;
    }

    public List<TopicDto> convert(List<Topic> topics){
        List<TopicDto> topicDtos = new ArrayList<>();

        for(Topic topic : topics) {
            topicDtos.add(convert(topic));
        }

        return topicDtos;
    }
}
