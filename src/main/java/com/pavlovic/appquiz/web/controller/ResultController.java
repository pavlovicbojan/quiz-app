package com.pavlovic.appquiz.web.controller;

import com.pavlovic.appquiz.exception.QuizApiException;
import com.pavlovic.appquiz.model.*;
import com.pavlovic.appquiz.service.ResultService;
import com.pavlovic.appquiz.service.TopicService;
import com.pavlovic.appquiz.service.UserService;
import com.pavlovic.appquiz.support.ResultToResultDto;
import com.pavlovic.appquiz.web.dto.ResultDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/result")
public class ResultController {

    @Autowired
    private ResultService resultService;

    @Autowired
    private TopicService topicService;

    @Autowired
    private UserService userService;

    @Autowired
    private ResultToResultDto toResultDto;



    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @RequestMapping(path = "/saveResult", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity saveResult(@RequestBody CheckResult checkResult) {
        Result result = new Result();
        result.setScore(checkResult.getScore());
        try {
            Topic topic = topicService.findOneById(checkResult.getTopicId()).get();
            result.setTopic(topic);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new QuizApiException(e.getMessage()));
        }

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        if(username == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new QuizApiException("Username not found!"));
        }
        Optional<User> user = userService.findbyUsername(username);
        if (user.isPresent()){
            result.setUser(user.get());
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new QuizApiException("User not found exception!"));
        }

        result.setDate(LocalDateTime.now());
        Result savedResult = resultService.save(result);
        ResultDto resultDto = toResultDto.convert(savedResult);
        return ResponseEntity.status(HttpStatus.CREATED).body(resultDto);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping(value = "/resultsByUserId")
    public ResponseEntity getAllResultsByUserId(@RequestParam(value = "pageNo", defaultValue = "0") int pageNo, Long userId){

        Page<Result> results = resultService.getAllByUserId(pageNo,userId);

        List<ResultDto> resultDtos = toResultDto.convert(results.getContent());

        PaginationResult paginationResult = new PaginationResult();
        paginationResult.setResults(resultDtos);
        paginationResult.setTotalPages(results.getTotalPages());

        return ResponseEntity.ok(paginationResult);
    }


    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping(value = "/results")
    public ResponseEntity getAllResultsByUser(@RequestParam(value = "pageNo", defaultValue = "0") int pageNo){

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        if(username == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new QuizApiException("Username not found!"));
        }
        Optional<User> user = userService.findbyUsername(username);

        Page<Result> results = resultService.getAllByUserId(pageNo,user.get().getId());

        List<ResultDto> resultDtos = toResultDto.convert(results.getContent());

        PaginationResult paginationResult = new PaginationResult();
        paginationResult.setResults(resultDtos);
        paginationResult.setTotalPages(results.getTotalPages());
//
////        Deleting questions from resultDto.
//        resultDtos.stream().forEach(resultDto -> {
//            List<QuestionDto> list = resultDto.getTopicDto().getQuestions();
//            list.removeAll(list);
//        });

        return ResponseEntity.ok(paginationResult);
    }

}
