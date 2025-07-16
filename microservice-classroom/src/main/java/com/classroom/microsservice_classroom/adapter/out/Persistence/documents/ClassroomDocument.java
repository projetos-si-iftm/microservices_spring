package com.classroom.microsservice_classroom.adapter.out.Persistence.documents;

import java.time.LocalDateTime;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

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
    @CreatedDate
    private LocalDateTime createIn;
    @LastModifiedDate
    private LocalDateTime updateIn;
    private boolean active;

}
