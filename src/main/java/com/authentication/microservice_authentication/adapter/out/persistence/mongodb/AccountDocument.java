package com.authentication.microservice_authentication.adapter.out.persistence.mongodb;


import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import com.authentication.microservice_authentication.domain.model.AccountRole;

import java.time.LocalDateTime;


@Data
@Document("accounts") 
public class AccountDocument {
    
    @Id
    private String id; 
    private String name;
    private String email;
    private String photo;
    private AccountRole role;
    private Boolean active;
    
    @CreatedDate
    private LocalDateTime createdAt;
    
    @LastModifiedDate
    private LocalDateTime updatedAt;
}