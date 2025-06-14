package com.classroom.microsservice_classroom.domain;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Classroom {

    private Integer id;
    private String title;
    private String subtitle;
    private String background;
    private String theme;
    private LocalDateTime createIn;
    private LocalDateTime updateIn;
    private boolean active;

}
