package com.pavlovic.appquiz.support;

import com.pavlovic.appquiz.model.Answer;
import com.pavlovic.appquiz.web.dto.AnswerDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AnswerToAnswerDto implements Converter<Answer, AnswerDto> {

    @Override
    public AnswerDto convert(Answer source) {
        AnswerDto answerDto = new AnswerDto();
        answerDto.setId(source.getId());
        answerDto.setAnswer(source.getAnswer());
        return answerDto;
    }

    public List<AnswerDto> convert(List<Answer> answers){
        List<AnswerDto> answerDtos = new ArrayList<>();
        for ( Answer answer : answers) {
            answerDtos.add(convert(answer));
        }

        return answerDtos;
    }
}
