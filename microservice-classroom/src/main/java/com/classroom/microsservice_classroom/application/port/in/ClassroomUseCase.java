package com.classroom.microsservice_classroom.application.port.in;




import java.util.List;

import org.springframework.http.ResponseEntity;


import com.classroom.microsservice_classroom.application.dto.ClassroomDTO;
import com.classroom.microsservice_classroom.application.dto.StudentDTO;




//embedding modeling
public interface ClassroomUseCase {
    ResponseEntity<ClassroomDTO> updateClassroom(ClassroomDTO request);
    ResponseEntity<ClassroomDTO> createClassroom(ClassroomDTO request);
    ResponseEntity<String> deleteClassroom(String id);
    ResponseEntity<List<ClassroomDTO>> findAllClassrooms();
    ResponseEntity<List<ClassroomDTO>> findClassroomsByStudentEmail(String studentEmail);
    ClassroomDTO addStudentToClassroom(String classroomCode, StudentDTO StudentDTO);
    ClassroomDTO getById(String id);
}
