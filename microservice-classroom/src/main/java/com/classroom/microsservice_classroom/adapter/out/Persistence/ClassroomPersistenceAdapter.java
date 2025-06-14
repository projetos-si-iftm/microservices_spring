package com.classroom.microsservice_classroom.adapter.out.Persistence;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.classroom.microsservice_classroom.domain.Classroom;
import com.classroom.microsservice_classroom.domain.port.out.ClassroomRepositoryPort;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ClassroomPersistenceAdapter implements ClassroomRepositoryPort {

    private final ClassroomMongoRepository repository;

    private ClassroomDocument toDocument(Classroom classroom) {
        ClassroomDocument doc = new ClassroomDocument();
        doc.setId(classroom.getId());
        doc.setTitle(classroom.getTitle());
        doc.setSubtitle(classroom.getSubtitle());
        doc.setTheme(classroom.getTheme());
        doc.setBackground(classroom.getBackground());
        doc.setActive(classroom.isActive());
        doc.setUpdateIn(classroom.getUpdateIn());
        doc.setCreateIn(classroom.getCreateIn());

        return doc;
    }

    private Classroom toDomain(ClassroomDocument doc) {
        return new Classroom(
                doc.getId(),
                doc.getTitle(),
                doc.getSubtitle(),
                doc.getBackground(),
                doc.getTheme(),
                doc.getCreateIn(),
                doc.getUpdateIn(),
                doc.isActive());
    }

    @Override
    public Classroom save(Classroom classroom) {
        ClassroomDocument document = toDocument(classroom);
        ClassroomDocument savDocument = repository.save(document);
        return toDomain(savDocument);
    }

    @Override
    public Optional<Classroom> findById(Integer id) {
        return repository.findById(id).map(this::toDomain);
    }

}