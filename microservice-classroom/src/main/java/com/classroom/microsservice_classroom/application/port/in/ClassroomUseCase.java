package com.classroom.microsservice_classroom.application.port.in;

import java.util.Optional;

import com.classroom.microsservice_classroom.application.dto.Request;
import com.classroom.microsservice_classroom.domain.Classroom;

public interface ClassroomUseCase {
    Classroom createOrUpdate(Request request);
    Optional<Classroom> getById(int id);
}
