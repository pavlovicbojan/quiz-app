package com.pavlovic.appquiz.service;

import com.pavlovic.appquiz.model.Result;
import com.pavlovic.appquiz.repository.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ResultServiceImpl implements ResultService{

    @Autowired
    private ResultRepository resultRepository;

    @Override
    public Result save(Result result) {
        return resultRepository.save(result);
    }

    @Override
    public Page<Result> getAllByUserId(int pageNo, Long userId) {
        return resultRepository.getAllByUserId(PageRequest.of(pageNo, 5), userId);
    }
}
