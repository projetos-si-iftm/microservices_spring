package com.classroom.microsservice_classroom.adapter.out.Persistence.documents;

import java.time.LocalDateTime;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document("Student")
public class StudentDocument {

    private String id;
    private String name;
    private String email;

    @CreatedDate
    private LocalDateTime ingress;
    @LastModifiedDate
    private LocalDateTime updateIn;
    private boolean active;

}
