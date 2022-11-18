package com.pavlovic.appquiz.repository;

import com.pavlovic.appquiz.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository< Question, Long> {

    List<Question> findAllByTopicId(Long topicId);
}
