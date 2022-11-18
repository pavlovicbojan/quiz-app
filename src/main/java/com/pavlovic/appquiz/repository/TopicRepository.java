package com.pavlovic.appquiz.repository;

import com.pavlovic.appquiz.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {

}
