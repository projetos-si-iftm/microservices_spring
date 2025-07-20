package com.classroom.microsservice_classroom.adapter.in;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.classroom.microsservice_classroom.adapter.in.response.StudentInsertCodeAndID;
import com.classroom.microsservice_classroom.application.dto.ClassroomDTO;
import com.classroom.microsservice_classroom.application.port.in.ClassroomUseCase;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.GetMapping;


@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ClassroomController {

    private final ClassroomUseCase useCase;

    @PostMapping("/save")
    public ResponseEntity<ClassroomDTO> save(@RequestBody @Valid ClassroomDTO request) {
        return useCase.createClassroom(request);
    }

    @PostMapping("/update")
    public ResponseEntity<ClassroomDTO> update(@RequestBody @Valid ClassroomDTO request) {
        return useCase.updateClassroom(request);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable String id){
        return useCase.deleteClassroom(id);
    }
    @GetMapping("/data")
    public ResponseEntity<List<ClassroomDTO>> getClassroom() {
        log.info(("usuario fez a requisição"));
        return useCase.findAllClassrooms();
    }
    
    @PostMapping("/insert")
    public ResponseEntity<List<ClassroomDTO>> insertStudentIntoClassroom(@RequestBody StudentInsertCodeAndID studentInsertCodeAndID) {
         useCase.addStudentToClassroom(studentInsertCodeAndID.code(), studentInsertCodeAndID.student());
         return useCase.findClassroomsByStudentEmail(studentInsertCodeAndID.student().getEmail());
    }

    @PostMapping("/invoke-classrooms/{email}")
    public ResponseEntity<List<ClassroomDTO>> invokeClassroomByStudentId(@PathVariable String email) {
        return useCase.findClassroomsByStudentEmail(email);
        
    }
    
    

  
    
    

}