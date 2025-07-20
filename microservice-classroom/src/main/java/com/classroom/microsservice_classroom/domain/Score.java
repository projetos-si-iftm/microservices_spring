package com.classroom.microsservice_classroom.domain;

import org.springframework.data.annotation.Id;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Score {
    

    @Id
    private String id;
    private Integer hitQuestion;
    private Integer total;
}
