package com.classroom.microsservice_classroom.application.dto;
import java.time.LocalDateTime;
import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ActivityDTO {

    private Integer id;
    private String type;
    private String title;
    private String description;

    private ScoreDTO score;
    private List<QuestionDTO> questions;

    private LocalDateTime createIn;
    private LocalDateTime updateIn;
    private boolean active;

}
