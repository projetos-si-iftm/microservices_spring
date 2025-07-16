package com.classroom.microsservice_classroom.domain;

import java.time.LocalDateTime;

import org.bson.types.ObjectId;

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
    private LocalDateTime createIn;
    private LocalDateTime updateIn;
    private boolean active; 

}
