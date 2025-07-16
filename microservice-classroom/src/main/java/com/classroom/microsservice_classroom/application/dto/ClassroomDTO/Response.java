package com.classroom.microsservice_classroom.application.dto.ClassroomDTO;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;

public class Response {
      @NotBlank(message = "{error.invalid.object}")
    private String name;
    @NotBlank(message = "{error.invalid.object}")
    private String description;
    @NotBlank(message = "{error.invalid.object}")
    private String image;
    @NotBlank(message = "{error.invalid.object}")
    private String code;
    private LocalDateTime createIn;
    private LocalDateTime updateIn;
    private boolean active;
}
