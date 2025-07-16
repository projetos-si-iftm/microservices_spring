package com.classroom.microsservice_classroom.adapter.out.Persistence.documents;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Document("Classroom")
public class ClassroomDocument {
    
    @Id
    private String id;
    private String name;
    private String description;
    private String image;
    private String code;
    @Size(max = 8)
    private List<SubjectDocument> subjects;
    @CreatedDate
    private LocalDateTime createIn;
    @LastModifiedDate
    private LocalDateTime updateIn;
    private boolean active;

}
