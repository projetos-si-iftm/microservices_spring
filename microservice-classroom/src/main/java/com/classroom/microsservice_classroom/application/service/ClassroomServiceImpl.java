package com.classroom.microsservice_classroom.application.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.classroom.microsservice_classroom.application.dto.ClassroomDTO;
import com.classroom.microsservice_classroom.application.port.in.ClassroomUseCase;
import com.classroom.microsservice_classroom.domain.Classroom;
import com.classroom.microsservice_classroom.domain.port.out.ClassroomRepositoryPort;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClassroomServiceImpl implements ClassroomUseCase {

    private final ClassroomRepositoryPort port;

    @Override
    public Optional<Classroom> getById(int id) {
        return port.findById(id);
    }

    private Classroom create(ClassroomDTO request) {
        Classroom classroom = Classroom.builder()
                .id(request.getId())
                .name(request.getName())
                .description(request.getDescription())
                .image(request.getImage())
                .code(request.getCode())
                .students(request.getStudents())
                .subjects(request.getSubjects())
                .createIn(LocalDateTime.now())
                .updateIn(LocalDateTime.now())
                .build();
        return port.save(classroom);
    }

    private Classroom update(ClassroomDTO request, Classroom classroom) {
        classroom.setId(request.getId());
        classroom.setName(request.getName());
        classroom.setDescription(request.getDescription());
        classroom.setImage(request.getImage());
        classroom.setCode(request.getCode());
        classroom.setStudents(request.getStudents());
        classroom.setSubjects(request.getSubjects());
        classroom.setCreateIn(request.getCreateIn());
        classroom.setUpdateIn(LocalDateTime.now());
        return port.save(classroom);
    }

    @Override
    public ResponseEntity<ClassroomDTO> createOrUpdate(ClassroomDTO request) {

        Optional<Classroom> classroom = port.findById(request.getId());
        if (classroom.isPresent()) {
            return new ResponseEntity<>(mapToResponse(update(request, classroom.get())), OK);
        } else if (classroom.isEmpty()) {
            return new ResponseEntity<>(mapToResponse(create(request)), CREATED);
        } else {
            return new ResponseEntity<>(BAD_REQUEST);
        }

    }
}
