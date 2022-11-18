package com.pavlovic.appquiz.support;

import com.pavlovic.appquiz.model.Result;
import com.pavlovic.appquiz.web.dto.ResultDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ResultToResultDto implements Converter<Result, ResultDto> {

    @Autowired
    private TopicToTopicDto topicDto;

    @Override
    public ResultDto convert(Result source) {
        ResultDto resultDto = new ResultDto();
        resultDto.setTopicDto(topicDto.convert(source.getTopic()));
        resultDto.setScore(source.getScore());
        resultDto.setLocalDateTime(source.getDate());
        return resultDto;
    }

    public List<ResultDto> convert(List<Result> results){
        List<ResultDto> resultDtos = new ArrayList<>();
        for (Result result : results){
            resultDtos.add(convert(result));
        }
        return resultDtos;
    }
}
