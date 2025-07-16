package com.classroom.microsservice_classroom.adapter.out.Persistence.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.classroom.microsservice_classroom.adapter.out.Persistence.documents.SubjectDocument;

public interface SubjectMongoRepository extends MongoRepository<SubjectDocument, String> {
}
