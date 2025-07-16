package com.classroom.microsservice_classroom.application.port.in;

import java.util.Optional;

import org.springframework.http.ResponseEntity;


import com.classroom.microsservice_classroom.application.dto.StudentDTO;
import com.classroom.microsservice_classroom.domain.Classroom;

public interface StudentUseCase {

    ResponseEntity<StudentDTO> createOrUpdate(StudentDTO request);

    Optional<Classroom> getById(int id);

}
