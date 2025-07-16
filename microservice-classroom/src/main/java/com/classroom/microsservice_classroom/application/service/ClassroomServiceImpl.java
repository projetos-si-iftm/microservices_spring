package com.classroom.microsservice_classroom.application.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.classroom.microsservice_classroom.application.dto.ClassroomDTO.ClassroomDTO;
import com.classroom.microsservice_classroom.application.port.in.ClassroomUseCase;
import com.classroom.microsservice_classroom.domain.Classroom;
import com.classroom.microsservice_classroom.domain.port.out.ClassroomRepositoryPort;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import java.util.UUID;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
// Classroom service implementa Classroom useCase
public class ClassroomServiceImpl implements ClassroomUseCase {

    private final ClassroomRepositoryPort classroomRepositoryPort;


       @Override
    public ResponseEntity<ClassroomDTO> updateClassroom(ClassroomDTO request) {
       return new ResponseEntity<>(update(request.getId(),request), OK );
    }




    @Override
    public ResponseEntity<ClassroomDTO> createClassroom(ClassroomDTO request) {
        return new ResponseEntity<>(create(request), CREATED );
    }

    @Override
    public ClassroomDTO getById(String id) {

        return classroomRepositoryPort.findById(id).map(this::mapToResponse).get();

    }

    private ClassroomDTO create(ClassroomDTO request) {
        String code = UUID.randomUUID().toString().substring(0, 6);

        Classroom classroom = Classroom.builder()
                .id(UUID.randomUUID().toString())
                .name(request.getName())
                .code(code)
                .description(request.getDescription())
                .image(request.getImage())
                .active(request.isActive())
                .createIn(LocalDateTime.now())
                .build();
        Classroom savedClassroom = classroomRepositoryPort.save(classroom);
        return mapToResponse(savedClassroom);

    }

    public ClassroomDTO update(String id, ClassroomDTO request) {
        Optional<Classroom> classroomExisting = classroomRepositoryPort.findById(request.getId());
        classroomExisting.get().setId(request.getId());
        classroomExisting.get().setName(request.getName());
        classroomExisting.get().setImage(request.getImage());
        classroomExisting.get().setCode(request.getCode());
        classroomExisting.get().setActive(request.isActive());
        classroomExisting.get().setDescription(request.getDescription());
        classroomExisting.get().setUpdateIn(LocalDateTime.now());

        Classroom updatedClassroom = classroomRepositoryPort.save(classroomExisting.get());
        return mapToResponse(updatedClassroom);
    }

    private ClassroomDTO mapToResponse(Classroom classroom) {
        return ClassroomDTO.builder()
                .id(classroom.getId())
                .name(classroom.getName())
                .image(classroom.getImage())
                .code(classroom.getCode())
                .description(classroom.getDescription())
                .createIn(classroom.getCreateIn())
                .updateIn(classroom.getUpdateIn())
                .active(classroom.isActive())
                .build();

    }




 

}
