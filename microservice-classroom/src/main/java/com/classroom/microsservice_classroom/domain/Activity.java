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
public class Activity {

    private String id;
    private String type;
    private String title;
    private String description;
    private Student student;
    private LocalDateTime createIn;
    private LocalDateTime updateIn;
    private boolean active;

}
