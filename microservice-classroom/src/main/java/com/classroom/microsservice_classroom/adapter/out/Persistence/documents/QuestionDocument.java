package com.classroom.microsservice_classroom.adapter.out.Persistence.documents;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document("Question")
public class QuestionDocument {

    private Integer id;
    private String questionText;
    private String[] options;
    private Integer correctOptionIndex;

}
