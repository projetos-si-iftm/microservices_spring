package com.classroom.microsservice_classroom.adapter.out.Persistence.documents;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document("Classroom")
public class ClassroomDocument {

    private Integer id;
    private String name;
    private String description;
    private String image;
    private String code;

    @DBRef
    private List<StudentDocument> students;
    @DBRef
    private List<SubjectDocument> subjects;

    @CreatedDate
    private LocalDateTime createIn;
    @LastModifiedDate
    private LocalDateTime updateIn;
    private boolean active;

}
