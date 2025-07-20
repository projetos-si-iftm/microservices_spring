package com.classroom.microsservice_classroom.adapter.out.Persistence;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.classroom.microsservice_classroom.adapter.out.Persistence.documents.ClassroomDocument;
import com.classroom.microsservice_classroom.adapter.out.Persistence.documents.StudentDocument;
import com.classroom.microsservice_classroom.adapter.out.Persistence.documents.SubjectDocument;
import com.classroom.microsservice_classroom.adapter.out.Persistence.repository.ClassroomMongoRepository;
import com.classroom.microsservice_classroom.domain.Classroom;
import com.classroom.microsservice_classroom.domain.Student;
import com.classroom.microsservice_classroom.domain.Subject;
import com.classroom.microsservice_classroom.domain.port.out.ClassroomRepositoryPort;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class ClassroomPersistenceAdapter implements ClassroomRepositoryPort {
    private final ClassroomMongoRepository mongoRepository;

    @Override
    public Classroom save(Classroom classroom) {
        ClassroomDocument document = toDocumentClassroom(classroom);
        ClassroomDocument saveDocument = mongoRepository.save(document);
        return toDomainClassroomDomain(saveDocument);
    }

    @Override
    public Optional<Classroom> findById(String id) {
        return mongoRepository.findById(id).map(this::toDomainClassroomDomain);
    }

    private ClassroomDocument toDocumentClassroom(Classroom classroom) {

        ClassroomDocument doc = new ClassroomDocument();
        List<SubjectDocument> subjectDocumentList = classroom.getSubjects().stream().map(this::toDocumentSubject)
                .toList();

        List<StudentDocument> studentsDocumentList = classroom.getStudents().stream().map(this::toDocumentStudent)
                .toList();

        doc.setId(classroom.getId());
        doc.setName(classroom.getName());
        doc.setImage(classroom.getImage());
        doc.setCode(classroom.getCode());
        doc.setDescription(classroom.getDescription());
        doc.setSubjects(subjectDocumentList);
        doc.setStudents(studentsDocumentList);
        doc.setCreateIn(classroom.getCreateIn());
        doc.setUpdateIn(classroom.getUpdateIn());
        doc.setActive(classroom.isActive());
        return doc;
    }

    private SubjectDocument toDocumentSubject(Subject subject) {
        SubjectDocument doc = new SubjectDocument();
        doc.setId(subject.getId());
        doc.setName(subject.getName());
        doc.setSubtitle(subject.getSubtitle());
        doc.setTitle(subject.getTitle());
        doc.setImageUrl(subject.getImageUrl());
        doc.setColorTheme(subject.getColorTheme());
        doc.setCreateIn(subject.getCreateIn());
        doc.setUpdateIn(subject.getUpdateIn());
        doc.setActive(subject.isActive());
        return doc;
    }
    private StudentDocument toDocumentStudent(Student student) {
        StudentDocument doc = new StudentDocument();
        doc.setId(student.getId());
        doc.setName(student.getName());
        doc.setEmail(student.getEmail());
        doc.setIngress(student.getIngress());
        doc.setUpdateIn(student.getUpdateIn());
        doc.setActive(student.isActive());
        return doc;
    }

    private Classroom toDomainClassroomDomain(ClassroomDocument classroomDocument) {
        List<Subject> subjects = classroomDocument.getSubjects().stream()
                .map(this::toSubjectDomain)
                .collect(Collectors.toList());
        List<Student> students = classroomDocument.getStudents().stream().map(this::toStudentsDomain)
                .collect(Collectors.toList());

        return Classroom.builder()
                .id(classroomDocument.getId())
                .name(classroomDocument.getName())
                .description(classroomDocument.getDescription())
                .image(classroomDocument.getImage())
                .code(classroomDocument.getCode())
                .subjects(subjects)
                .students(students)
                .createIn(classroomDocument.getCreateIn())
                .updateIn(classroomDocument.getUpdateIn())
                .active(classroomDocument.isActive())
                .build();
    }

    private Subject toSubjectDomain(SubjectDocument subjectDocument) {

        return Subject.builder()
                .id(subjectDocument.getId())
                .name(subjectDocument.getName())
                .title(subjectDocument.getTitle())
                .colorTheme(subjectDocument.getColorTheme())
                .subtitle(subjectDocument.getSubtitle())
                .imageUrl(subjectDocument.getImageUrl())
                .createIn(subjectDocument.getCreateIn())
                .updateIn(subjectDocument.getUpdateIn())
                .active(subjectDocument.isActive())
                .build();
    }

    private Student toStudentsDomain(StudentDocument studentDocument) {
        return Student.builder()
                .id(studentDocument.getId())
                .email(studentDocument.getEmail())
                .active(studentDocument.isActive())
                .ingress(studentDocument.getIngress())
                .updateIn(studentDocument.getUpdateIn())
                .build();

    }

    @Override
    public String delete(String id) {
        ClassroomDocument classroom = mongoRepository.findById(id).get();
        mongoRepository.delete(mongoRepository.findById(id).get());
        String response = classroom.getName();
        return "The classroom called [" + response + "] has been deleted sucessfully";
    }

    @Override
    public List<Classroom> findAll() {
        List<ClassroomDocument> classrooms = mongoRepository.findAll();
        return classrooms.stream().map(this::toDomainClassroomDomain).toList();

    }

    @Override
    public Classroom findClassroomBycode(String code) {
        ClassroomDocument classroom = mongoRepository.findByCode(code);
        return toDomainClassroomDomain(classroom);
    }

    @Override
    public List<Classroom> findByStudentEmail(String studentEmail) {
        List<ClassroomDocument> listOfDocuments = mongoRepository.findByStudentsEmail(studentEmail);
        return listOfDocuments.stream().map(l -> toDomainClassroomDomain(l)).toList();
    }

}