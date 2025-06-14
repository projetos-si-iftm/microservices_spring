package com.classroom.microsservice_classroom.application.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Request {

    private Integer id;
    private String title;
    private String subtitle;
    private String background;
    private String theme;
    private boolean active;

}
