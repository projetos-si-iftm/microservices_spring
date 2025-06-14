package com.classroom.microsservice_classroom.domain.port.out;

import java.util.Optional;

import com.classroom.microsservice_classroom.domain.Classroom;

public interface ClassroomRepositoryPort {
    Classroom save(Classroom classroom);
    Optional<Classroom> findById(Integer id);
}