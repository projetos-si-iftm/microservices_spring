package com.authentication.microservice_authentication.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@Configuration
@EnableMongoAuditing // Habilita a mágica do @CreatedDate e @LastModifiedDate
public class MongoConfig {
}