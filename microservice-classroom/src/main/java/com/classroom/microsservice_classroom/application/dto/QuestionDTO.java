package com.classroom.microsservice_classroom.application.dto;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class QuestionDTO {

    private Integer id;
    private String questionText;
    private List<String> options;
    private Integer correctOptionIndex;

}
