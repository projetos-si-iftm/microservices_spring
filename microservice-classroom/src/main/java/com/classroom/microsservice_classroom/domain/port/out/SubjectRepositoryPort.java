package com.classroom.microsservice_classroom.domain.port.out;

import java.util.List;
import java.util.Optional;
import com.classroom.microsservice_classroom.domain.Subject;

public interface SubjectRepositoryPort {

    Subject save(Subject subject);

    Optional<Subject> findById(String id);
}
