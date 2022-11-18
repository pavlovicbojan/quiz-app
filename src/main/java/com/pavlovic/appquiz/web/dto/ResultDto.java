package com.pavlovic.appquiz.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ResultDto {

    private int score;

    private TopicDto topicDto;

    private LocalDateTime localDateTime;

}
