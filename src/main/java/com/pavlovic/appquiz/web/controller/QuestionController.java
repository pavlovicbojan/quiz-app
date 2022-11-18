package com.pavlovic.appquiz.web.controller;

import com.pavlovic.appquiz.model.CheckAnswer;
import com.pavlovic.appquiz.model.Question;
import com.pavlovic.appquiz.service.QuestionService;
import com.pavlovic.appquiz.support.QuestionToQuestionDto;
import com.pavlovic.appquiz.web.dto.QuestionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/question")
public class QuestionController {

    @Autowired
    private QuestionToQuestionDto toQuestionDto;
    @Autowired
    private QuestionService questionService;

    @GetMapping(value = "/{topicId}")
    public ResponseEntity<List<QuestionDto>> getQuestionsByTopicId(@PathVariable Long topicId){
        List<Question> questions = questionService.findAllByTopicId(topicId);
        List<QuestionDto> questionDtos = toQuestionDto.convert(questions);
        return ResponseEntity.ok().body(questionDtos);
    }

    @RequestMapping(path = "/checkAnswer", method = RequestMethod.POST)
    public ResponseEntity<Boolean> checkAnswer(@RequestBody CheckAnswer checkAnswer) {
        Question question = questionService.findOneById(checkAnswer.getQuestionId()).get();

        boolean isCorrect = false;
        if(question.getCorrectAnswerId().equals(checkAnswer.getAnswerId())){
            isCorrect = true;
        }
        return ResponseEntity.ok().body(isCorrect);
    }
}
