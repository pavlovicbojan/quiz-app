package com.pavlovic.appquiz.repository;

import com.pavlovic.appquiz.model.Result;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResultRepository extends JpaRepository<Result, Long> {

    Page<Result> getAllByUserId(Pageable pageable, Long userId);


}
