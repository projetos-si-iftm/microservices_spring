package com.classroom.microsservice_classroom.application.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ScoreDTO {
    private Integer obtained;
    private Integer total;
}
