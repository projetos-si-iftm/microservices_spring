package com.classroom.microsservice_classroom.application.port.in;

import java.util.Optional;


import org.springframework.http.ResponseEntity;

import com.classroom.microsservice_classroom.application.dto.ClassroomDTO;
import com.classroom.microsservice_classroom.domain.Classroom;

public interface ClassroomUseCase {
    ResponseEntity<ClassroomDTO> createOrUpdate(ClassroomDTO request);
    Optional<Classroom> getById(int id);
}
