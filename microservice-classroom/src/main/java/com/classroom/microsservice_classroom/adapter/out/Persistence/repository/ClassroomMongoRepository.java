package com.classroom.microsservice_classroom.adapter.out.Persistence.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.classroom.microsservice_classroom.adapter.out.Persistence.documents.ClassroomDocument;

public interface ClassroomMongoRepository extends MongoRepository<ClassroomDocument, String> {
}
