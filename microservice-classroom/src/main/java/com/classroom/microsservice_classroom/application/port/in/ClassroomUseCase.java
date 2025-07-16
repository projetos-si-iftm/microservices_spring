package com.classroom.microsservice_classroom.application.port.in;


import org.springframework.http.ResponseEntity;

import com.classroom.microsservice_classroom.application.dto.ClassroomDTO.ClassroomDTO;


public interface ClassroomUseCase {

    ResponseEntity<ClassroomDTO> updateClassroom(ClassroomDTO request);
    ResponseEntity<ClassroomDTO> createClassroom(ClassroomDTO request);
    ClassroomDTO getById(String id);
}
