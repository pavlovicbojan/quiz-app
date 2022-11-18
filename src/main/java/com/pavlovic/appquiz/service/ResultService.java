package com.pavlovic.appquiz.service;

import com.pavlovic.appquiz.model.Result;
import org.springframework.data.domain.Page;

public interface ResultService {

    Result save(Result result);

    Page<Result> getAllByUserId(int pageNo, Long userId);
}
