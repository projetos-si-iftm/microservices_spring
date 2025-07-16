package com.classroom.microsservice_classroom.adapter.out.Persistence.documents;

import java.time.LocalDateTime;


import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document("Activity")
public class ActivityDocument {

    private String id;
    private String type;
    private String title;
    private String description;

    @CreatedDate
    private LocalDateTime createIn;
    @LastModifiedDate
    private LocalDateTime updateIn;
    private boolean active;

}
