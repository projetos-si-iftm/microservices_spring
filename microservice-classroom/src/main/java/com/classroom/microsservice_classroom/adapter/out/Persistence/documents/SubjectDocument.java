package com.classroom.microsservice_classroom.adapter.out.Persistence.documents;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document("Subject")
public class SubjectDocument {

    private String id;
    private String name;
    private String title;
    private String colorTheme;
    private String subtitle;
    private String imageUrl;
    

    @CreatedDate
    private LocalDateTime createIn;
    @LastModifiedDate
    private LocalDateTime updateIn;

    private boolean active;
}
