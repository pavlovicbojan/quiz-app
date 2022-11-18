package com.pavlovic.appquiz.model;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class CheckResult {
    @NonNull
    private int score;
    @NonNull
    private Long topicId;
}
