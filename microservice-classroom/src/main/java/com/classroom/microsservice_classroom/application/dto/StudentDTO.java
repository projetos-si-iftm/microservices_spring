package com.classroom.microsservice_classroom.application.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StudentDTO {

    private String id;
    private String name;
    private String email;

    private LocalDateTime ingress;
    private LocalDateTime updateIn;
    private boolean active;

}
