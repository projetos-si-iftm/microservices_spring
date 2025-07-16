package com.classroom.microsservice_classroom.domain.port.out;

import java.util.Optional;

import org.bson.types.ObjectId;

import com.classroom.microsservice_classroom.domain.Classroom;

public interface ClassroomRepositoryPort {

    Classroom save(Classroom classroom);
    String delete(String id);
    Optional<Classroom> findById(String id);
}