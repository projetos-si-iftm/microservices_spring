package com.classroom.microsservice_classroom.domain.port.out;

import java.util.List;
import java.util.Optional;



import com.classroom.microsservice_classroom.domain.Classroom;

public interface ClassroomRepositoryPort {

    Classroom save(Classroom classroom);
    String delete(String id);
    Classroom findClassroomBycode(String code);
    List<Classroom> findAll();
    List<Classroom> findByStudentEmail(String studentEmail);
    Optional<Classroom> findById(String id);
}