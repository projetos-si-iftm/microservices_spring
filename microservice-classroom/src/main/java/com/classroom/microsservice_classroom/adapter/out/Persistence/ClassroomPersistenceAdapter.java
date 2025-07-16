package com.classroom.microsservice_classroom.adapter.out.Persistence;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

import com.classroom.microsservice_classroom.adapter.out.Persistence.documents.ClassroomDocument;
import com.classroom.microsservice_classroom.adapter.out.Persistence.repository.ClassroomMongoRepository;
import com.classroom.microsservice_classroom.domain.Classroom;
import com.classroom.microsservice_classroom.domain.port.out.ClassroomRepositoryPort;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ClassroomPersistenceAdapter implements ClassroomRepositoryPort {

    private final ClassroomMongoRepository mongoRepository;

    private ClassroomDocument toDocument(Classroom classroom) {
        ClassroomDocument doc = new ClassroomDocument();

        doc.setId(classroom.getId());
        doc.setName(classroom.getName());
        doc.setImage(classroom.getImage());
        doc.setCode(classroom.getCode());
        doc.setDescription(classroom.getDescription());
        doc.setCreateIn(classroom.getCreateIn());
        doc.setUpdateIn(classroom.getUpdateIn());
        doc.setActive(classroom.isActive());
        return doc;
    }

    private Classroom toDomain(ClassroomDocument document) {
        return new Classroom(
                document.getId(),
                document.getName(),
                document.getDescription(),
                document.getImage(),
                document.getCode(),
                document.getCreateIn(),
                document.getUpdateIn(),
                document.isActive());
    }

    @Override
    public Classroom save(Classroom classroom) {
        ClassroomDocument document = toDocument(classroom);
        ClassroomDocument saveDocument = mongoRepository.save(document);
        return toDomain(saveDocument);
    }

    @Override
    public Optional<Classroom> findById(String id) {
        return mongoRepository.findById(id).map(this::toDomain);
    }

}