package com.classroom.microsservice_classroom.adapter.in.response;

import com.classroom.microsservice_classroom.application.dto.StudentDTO;

public record StudentInsertCodeAndID (String code, StudentDTO student){}
