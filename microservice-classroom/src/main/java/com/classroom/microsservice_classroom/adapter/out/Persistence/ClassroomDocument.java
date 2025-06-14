package com.classroom.microsservice_classroom.adapter.out.Persistence;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document("Classroom")
public class ClassroomDocument {

    private Integer id;
    private String title;
    private String subtitle;
    private String background;
    private String theme;
    @CreatedDate
    private LocalDateTime createIn;
    @LastModifiedDate
    private LocalDateTime updateIn;
    private boolean active;

}
