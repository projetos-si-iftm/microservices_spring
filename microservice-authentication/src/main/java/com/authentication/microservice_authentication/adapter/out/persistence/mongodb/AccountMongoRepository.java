package com.authentication.microservice_authentication.adapter.out.persistence.mongodb;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface AccountMongoRepository extends MongoRepository<AccountDocument, String> {
    Optional<AccountDocument> findByEmail(String email);
}