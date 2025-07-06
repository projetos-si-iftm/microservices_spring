package com.classroom.microsservice_classroom.adapter.out.Persistence.documents;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document("Score")
public class ScoreDocument {

    private Integer obtained;
    private Integer total;

}
