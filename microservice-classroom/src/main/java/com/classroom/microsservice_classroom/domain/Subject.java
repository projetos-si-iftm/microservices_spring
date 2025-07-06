package com.classroom.microsservice_classroom.domain;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Subject {

    private Integer id;
    private String name;
    private String title;
    private String colorTheme;
    private String subtitle;
    private String imageUrl;
    
    private List<Activity> activities;

    private LocalDateTime createIn;
    private LocalDateTime updateIn;
    private boolean active;

}
