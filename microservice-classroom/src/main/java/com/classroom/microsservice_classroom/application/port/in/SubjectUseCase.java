package com.classroom.microsservice_classroom.application.port.in;

import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.classroom.microsservice_classroom.application.dto.SubjectDTO;
import com.classroom.microsservice_classroom.domain.Classroom;

public interface SubjectUseCase {
    ResponseEntity<SubjectDTO> createOrUpdate(SubjectDTO request);

    Optional<Classroom> getById(int id);
}
