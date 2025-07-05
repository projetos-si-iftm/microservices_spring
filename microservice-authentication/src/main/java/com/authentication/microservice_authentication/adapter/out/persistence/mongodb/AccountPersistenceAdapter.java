package com.authentication.microservice_authentication.adapter.out.persistence.mongodb;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import com.authentication.microservice_authentication.application.dto.AccountNameAndPhoto;
import com.authentication.microservice_authentication.domain.model.Account;

import com.authentication.microservice_authentication.domain.port.out.AccountRepositoryPort;

import java.util.List;
import java.util.Optional;

@Component 
@RequiredArgsConstructor
public class AccountPersistenceAdapter implements AccountRepositoryPort{

    private final AccountMongoRepository mongoRepository;
    

    private AccountDocument toDocument(Account account) {
        AccountDocument doc = new AccountDocument();
        doc.setId(account.getId());
        doc.setName(account.getName());
        doc.setEmail(account.getEmail());
        doc.setPhoto(account.getPhoto());
        doc.setRole(account.getRole());
        doc.setActive(account.getActive());
        doc.setCreatedAt(account.getCreatedAt());
        doc.setUpdatedAt(account.getUpdatedAt());
        return doc;
    }

    private Account toDomain(AccountDocument document) {
        return new Account(
            document.getId(),
            document.getName(),
            document.getEmail(),
            document.getPhoto(),
            document.getRole(),
            document.getActive(),
            document.getCreatedAt(),
            document.getUpdatedAt()
        );
    }

    @Override
    public Account save(Account account) {
        AccountDocument document = toDocument(account);
        AccountDocument savedDocument = mongoRepository.save(document);
        return toDomain(savedDocument);
    }

    @Override
    public Optional<Account> findById(String id) {
        return mongoRepository.findById(id).map(this::toDomain);
    }
  
    @Override
    public Optional<Account> findByEmail(String email) {
        return mongoRepository.findByEmail(email).map(this::toDomain);
    }


    @Override
    public List<AccountNameAndPhoto> findAll() {
        return mongoRepository.findAll().stream().map(acc -> new AccountNameAndPhoto(acc.getId(), acc.getName(), acc.getPhoto())).toList();
    }

 
  

   
}