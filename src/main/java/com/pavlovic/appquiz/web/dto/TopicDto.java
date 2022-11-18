package com.pavlovic.appquiz.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class TopicDto {

    private Long id;

    private String name;

    private List<QuestionDto> questions;
}
