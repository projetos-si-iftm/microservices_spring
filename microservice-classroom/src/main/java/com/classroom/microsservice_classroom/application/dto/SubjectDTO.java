package com.classroom.microsservice_classroom.application.dto;
import java.time.LocalDateTime;
import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SubjectDTO {

    private Integer id;
    private String name;
    private String title;
    private String colorTheme;
    private String subtitle;
    private String imageUrl;
    
    private List<ActivityDTO> activities;

    private LocalDateTime createIn;
    private LocalDateTime updateIn;
    private boolean active;

}
