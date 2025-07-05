package com.classroom.microsservice_classroom.application.port.in;

import java.util.Optional;


import org.springframework.http.ResponseEntity;

import com.classroom.microsservice_classroom.application.dto.Request;
import com.classroom.microsservice_classroom.domain.Classroom;

public interface ClassroomUseCase {

    ResponseEntity<Request> createOrUpdate(Request request);
    Optional<Classroom> getById(int id);
}
