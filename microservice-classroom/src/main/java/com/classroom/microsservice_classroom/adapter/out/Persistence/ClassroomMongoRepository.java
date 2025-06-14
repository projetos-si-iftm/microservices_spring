package com.classroom.microsservice_classroom.adapter.out.Persistence;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ClassroomMongoRepository extends MongoRepository<ClassroomDocument, Integer> {
}
