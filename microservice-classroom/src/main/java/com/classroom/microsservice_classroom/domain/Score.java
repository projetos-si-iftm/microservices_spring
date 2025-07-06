package com.classroom.microsservice_classroom.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Score {
    private Integer obtained;
    private Integer total;
}
