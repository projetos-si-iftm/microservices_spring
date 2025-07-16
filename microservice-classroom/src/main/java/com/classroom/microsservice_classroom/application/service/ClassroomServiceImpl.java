package com.classroom.microsservice_classroom.application.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.classroom.microsservice_classroom.application.dto.SubjectDTO;
import com.classroom.microsservice_classroom.application.dto.ClassroomDTO.ClassroomDTO;
import com.classroom.microsservice_classroom.application.port.in.ClassroomUseCase;
import com.classroom.microsservice_classroom.domain.Classroom;
import com.classroom.microsservice_classroom.domain.Subject;
import com.classroom.microsservice_classroom.domain.port.out.ClassroomRepositoryPort;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import java.util.UUID;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
// Classroom service implementa Classroom useCase
public class ClassroomServiceImpl implements ClassroomUseCase {

    private final ClassroomRepositoryPort classroomRepositoryPort;

    @Override
    public ResponseEntity<ClassroomDTO> updateClassroom(ClassroomDTO request) {
        return new ResponseEntity<>(update(request.getId(), request), OK);
    }

    @Override
    public ResponseEntity<ClassroomDTO> createClassroom(ClassroomDTO request) {
        return new ResponseEntity<>(create(request), CREATED);
    }

    @Override
    public ResponseEntity<String> deleteClassroom(String id) {
        String response =  classroomRepositoryPort.delete(id);
        return new ResponseEntity<>(response,OK);
    }

    @Override
    public ClassroomDTO getById(String id) {

        return classroomRepositoryPort.findById(id).map(this::mapToResponse).get();

    }

    private ClassroomDTO create(ClassroomDTO request) {
        String code = UUID.randomUUID().toString().substring(0, 6);

        List<Subject> subjectsDomainList = request.getSubjects().stream()
                .map(this::mapToSubjectDomain)
                .collect(Collectors.toList());

        Classroom classroom = Classroom.builder()
                .id(UUID.randomUUID().toString())
                .name(request.getName())
                .code(code)
                .subjects(subjectsDomainList)
                .description(request.getDescription())
                .image(request.getImage())
                .active(request.isActive())
                .createIn(LocalDateTime.now())
                .build();
        Classroom savedClassroom = classroomRepositoryPort.save(classroom);
        return mapToResponse(savedClassroom);

    }

    private ClassroomDTO update(String id, ClassroomDTO request) {
        Classroom classroom = classroomRepositoryPort.findById(id).get();

        classroom.setName(request.getName());
        classroom.setDescription(request.getDescription());
        classroom.setImage(request.getImage());
        classroom.setCode(request.getCode());
        classroom.setActive(request.isActive());
        classroom.setUpdateIn(LocalDateTime.now());

        if (request.getSubjects() != null) {

            List<Subject> updatedSubjects = syncSubjects(request.getSubjects(), classroom.getSubjects());
            classroom.setSubjects(updatedSubjects);
        } else {

            classroom.setSubjects(new ArrayList<>());
        }

        Classroom updatedClassroom = classroomRepositoryPort.save(classroom);

        return mapToResponse(updatedClassroom);
    }

    private ClassroomDTO mapToResponse(Classroom classroom) {

        List<SubjectDTO> subjectDTOs = classroom.getSubjects().stream()
                .map(this::mapToSubjectDTOResponse)
                .collect(Collectors.toList());

        return ClassroomDTO.builder()
                .id(classroom.getId())
                .name(classroom.getName())
                .image(classroom.getImage())
                .code(classroom.getCode())
                .description(classroom.getDescription())
                .subjects(subjectDTOs)
                .createIn(classroom.getCreateIn())
                .updateIn(classroom.getUpdateIn())
                .active(classroom.isActive())
                .build();

    }

    private Subject mapToSubjectDomain(SubjectDTO dto) {
        return Subject.builder()
                .id(UUID.randomUUID().toString().substring(0, 7))
                .name(dto.getName())
                .title(dto.getTitle())
                .colorTheme(dto.getColorTheme())
                .subtitle(dto.getSubtitle())
                .imageUrl(dto.getImageUrl())
                .createIn(LocalDateTime.now())
                .active(true)
                .build();
    }

    private SubjectDTO mapToSubjectDTOResponse(Subject subject) {
        return SubjectDTO.builder()
                .id(subject.getId())
                .name(subject.getName())
                .title(subject.getTitle())
                .colorTheme(subject.getColorTheme())
                .subtitle(subject.getSubtitle())
                .imageUrl(subject.getImageUrl())
                .createIn(subject.getCreateIn())
                .active(subject.isActive())
                .build();
    }

    private List<Subject> syncSubjects(List<SubjectDTO> subjectDTOsFromRequest, List<Subject> existingSubjects) {
        if (existingSubjects == null) {
            existingSubjects = new ArrayList<>();
        }
        Map<String, Subject> existingSubjectsMap = existingSubjects.stream()
                .collect(Collectors.toMap(Subject::getId, subject -> subject));

        return subjectDTOsFromRequest.stream().map(dto -> {
            if (dto.getId() != null && existingSubjectsMap.containsKey(dto.getId())) {
                Subject subjectToUpdate = existingSubjectsMap.get(dto.getId());
                subjectToUpdate.setName(dto.getName());
                subjectToUpdate.setTitle(dto.getTitle());
                subjectToUpdate.setColorTheme(dto.getColorTheme());
                subjectToUpdate.setSubtitle(dto.getSubtitle());
                subjectToUpdate.setImageUrl(dto.getImageUrl());
                subjectToUpdate.setActive(dto.isActive());
                subjectToUpdate.setUpdateIn(LocalDateTime.now());
                return subjectToUpdate;
            }

            else {
                return mapToSubjectDomain(dto);
            }
        }).collect(Collectors.toList());
    }


}
