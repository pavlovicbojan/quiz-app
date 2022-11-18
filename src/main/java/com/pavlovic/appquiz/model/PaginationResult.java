package com.pavlovic.appquiz.model;

import com.pavlovic.appquiz.web.dto.ResultDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PaginationResult {

    private List<ResultDto> results;

    private int totalPages;
}
