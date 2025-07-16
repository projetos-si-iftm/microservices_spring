package com.classroom.microsservice_classroom.application.dto;

import java.time.LocalDateTime;


import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SubjectDTO {

    private String id;
    private String name;
    private String title;
    private String colorTheme;
    private String subtitle;
    private String imageUrl;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm", timezone = "America/Sao_Paulo")
    private LocalDateTime createIn;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm", timezone = "America/Sao_Paulo")
    private LocalDateTime updateIn;
    private boolean active;

}
