package com.classroom.microsservice_classroom.domain;

import java.time.LocalDateTime;
import java.util.List;


import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Classroom {

    private String id;
    private String name;
    private String description;
    private String image;
    private String code; 
    @Size(max = 8)
    private List<Subject> subjects;
    private LocalDateTime createIn;
    private LocalDateTime updateIn;
    private boolean active; 

}
