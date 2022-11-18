package com.pavlovic.appquiz.support;

import com.pavlovic.appquiz.model.Question;
import com.pavlovic.appquiz.web.dto.QuestionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class QuestionToQuestionDto implements Converter<Question, QuestionDto> {

    @Autowired
    private AnswerToAnswerDto toAnswerDto;

    @Override
    public QuestionDto convert(Question source) {
        QuestionDto questionDto = new QuestionDto();
        questionDto.setId(source.getId());
        questionDto.setTitle(source.getTitle());
        questionDto.setAnswerDtos(toAnswerDto.convert(source.getAnswers()));
        return questionDto;
    }

    public List<QuestionDto> convert(List<Question> questions){
        List<QuestionDto> questionDtos = new ArrayList<>();

        for(Question question : questions) {
            questionDtos.add(convert(question));
        }

        return questionDtos;
    }


}
