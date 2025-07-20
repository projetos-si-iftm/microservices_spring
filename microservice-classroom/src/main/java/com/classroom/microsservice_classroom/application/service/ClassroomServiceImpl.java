package com.classroom.microsservice_classroom.application.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.classroom.microsservice_classroom.application.dto.ActivityDTO;
import com.classroom.microsservice_classroom.application.dto.ClassroomDTO;
import com.classroom.microsservice_classroom.application.dto.StudentDTO;
import com.classroom.microsservice_classroom.application.dto.SubjectDTO;
import com.classroom.microsservice_classroom.application.port.in.ClassroomUseCase;
import com.classroom.microsservice_classroom.domain.Activity;
import com.classroom.microsservice_classroom.domain.Classroom;
import com.classroom.microsservice_classroom.domain.Student;
import com.classroom.microsservice_classroom.domain.Subject;
import com.classroom.microsservice_classroom.domain.port.out.ClassroomRepositoryPort;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import java.util.UUID;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@Slf4j
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
        String response = classroomRepositoryPort.delete(id);
        return new ResponseEntity<>(response, OK);
    }

    @Override
    public ResponseEntity<List<ClassroomDTO>> findAllClassrooms() {
        List<Classroom> classrooms = classroomRepositoryPort.findAll();
        List<ClassroomDTO> classroomsLstDTO = classrooms.stream().map(classList -> {
            return mapToClassroomDTO(classList);
        }).toList();
        return new ResponseEntity<>(classroomsLstDTO, OK);
    }

    @Override
    public ClassroomDTO getById(String id) {

        return classroomRepositoryPort.findById(id).map(this::mapToClassroomDTO).get();

    }

    @Override
    public ClassroomDTO addStudentToClassroom(String classroomCode, StudentDTO studentDTO) {
        
        Classroom classroom = classroomRepositoryPort.findClassroomBycode(classroomCode);
        
        log.info("Classroom {}",classroom);


        boolean studentExists = classroom.getStudents().stream()
                .anyMatch(student -> student.getEmail().equalsIgnoreCase(studentDTO.getEmail()));

        if (studentExists) {
            throw new RuntimeException("Aluno com o email " + studentDTO.getEmail() + " já está na turma.");
        }

        Student newStudent = mapToStudentDomain(studentDTO);

        newStudent.setId(studentDTO.getId());
        newStudent.setName(studentDTO.getName());
        newStudent.setEmail(studentDTO.getEmail());
        newStudent.setIngress(java.time.LocalDateTime.now());
        newStudent.setActive(true);
        classroom.getStudents().add(newStudent);
        Classroom classroomSaved  = classroomRepositoryPort.save(classroom);
    
        return mapToClassroomDTO(classroomSaved);
    }


      @Override
    public ResponseEntity<List<ClassroomDTO>> findClassroomsByStudentEmail(String studentEmail) {
        log.info("Chamei a funcao de achar todos estudantes");
        List<ClassroomDTO> classroomDTOs =  classroomRepositoryPort.findByStudentEmail(studentEmail).stream()
                .map(this::mapToClassroomDTO) 
                .collect(Collectors.toList());
        return new ResponseEntity<>(classroomDTOs, OK);
    }

    private ClassroomDTO create(ClassroomDTO request) {
        String code = UUID.randomUUID().toString().substring(0, 6);

        List<Subject> subjectsDomainList = request.getSubjects().stream()
                .map(this::mapToSubjectDomain)
                .toList();

        List<Student> studentsDomainList = request.getStudents().stream()
                .map(this::mapToStudentDomain).toList();

        Classroom classroom = Classroom.builder()
                .id(UUID.randomUUID().toString())
                .name(request.getName())
                .code(code)
                .subjects(subjectsDomainList)
                .students(studentsDomainList)
                .description(request.getDescription())
                .image(request.getImage())
                .active(request.isActive())
                .createIn(LocalDateTime.now())
                .build();

        Classroom savedClassroom = classroomRepositoryPort.save(classroom);
        return mapToClassroomDTO(savedClassroom);

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

        return mapToClassroomDTO(updatedClassroom);
    }

    private ClassroomDTO mapToClassroomDTO(Classroom classroom) {

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

    private StudentDTO mapStudentDTOResponse(Student student) {
        return StudentDTO.builder()
                .id(student.getId())
                .name(student.getName())
                .email(student.getEmail())
                .ingress(student.getIngress())
                .updateIn(student.getUpdateIn())
                .active(student.isActive())
                .build();

    }

    private Activity mapToActivityDomain(ActivityDTO request) {
        return Activity.builder()
                .id(UUID.randomUUID().toString().substring(0, 11))
                .description(request.getDescription())
                .title(request.getTitle())
                .type(request.getType())
                .active(false)
                .createIn(null)
                .createIn(null)
                .build();

    }

    private Student mapToStudentDomain(StudentDTO request) {
        return Student.builder()
                .id(request.getId())
                .name(request.getName())
                .email(request.getEmail())
                .ingress(request.getIngress())
                .updateIn(request.getUpdateIn())
                .active(request.isActive())
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
