package com.classroom.microsservice_classroom.domain;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    private String id;
    private String name;
    private String email;

    private LocalDateTime ingress;
    private LocalDateTime updateIn;
    private boolean active;

}
