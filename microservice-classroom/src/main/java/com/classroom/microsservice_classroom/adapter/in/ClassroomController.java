package com.classroom.microsservice_classroom.adapter.in;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

import com.classroom.microsservice_classroom.application.dto.ClassroomDTO.ClassroomDTO;
import com.classroom.microsservice_classroom.application.port.in.ClassroomUseCase;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

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

}