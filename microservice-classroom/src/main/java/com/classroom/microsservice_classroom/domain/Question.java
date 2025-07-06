package com.classroom.microsservice_classroom.domain;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Question {

    private Integer id;
    private String questionText;
    private List<String> options;
    private Integer correctOptionIndex;

}
