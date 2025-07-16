package com.classroom.microsservice_classroom.domain.port.out;

import java.util.Optional;
import com.classroom.microsservice_classroom.domain.Student;

public interface StudentRepositoryPort {
    
    Student save(Student student);

    Optional<Student> findById(Integer id);
}
