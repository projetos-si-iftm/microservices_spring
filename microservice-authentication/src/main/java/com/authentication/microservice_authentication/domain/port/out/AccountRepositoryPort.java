package com.authentication.microservice_authentication.domain.port.out;


import java.util.List;
import java.util.Optional;

import com.authentication.microservice_authentication.application.dto.AccountNameAndPhoto;
import com.authentication.microservice_authentication.domain.model.Account;

public interface AccountRepositoryPort {

    Account save(Account account);
    Optional<Account> findById(String id);
    Optional<Account> findByEmail(String email);
    List<AccountNameAndPhoto> findAll();
}