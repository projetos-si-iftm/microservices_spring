package com.classroom.microsservice_classroom.application.port.in;


import java.util.List;

import org.springframework.http.ResponseEntity;

import com.classroom.microsservice_classroom.application.dto.SubjectDTO;
import com.classroom.microsservice_classroom.application.dto.ClassroomDTO.ClassroomDTO;

//embedding modeling
public interface ClassroomUseCase {
    ResponseEntity<ClassroomDTO> updateClassroom(ClassroomDTO request);
    ResponseEntity<ClassroomDTO> createClassroom(ClassroomDTO request);
    ResponseEntity<String> deleteClassroom(String id);
    ClassroomDTO getById(String id);
}
