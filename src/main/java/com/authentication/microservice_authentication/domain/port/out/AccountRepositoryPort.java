package com.authentication.microservice_authentication.domain.port.out;


import java.util.Optional;
import com.authentication.microservice_authentication.domain.model.Account;

// Definindo o que desejo fazer na aplicação
public interface AccountRepositoryPort {

    Account save(Account account);

    Optional<Account> findById(String id);

    Optional<Account> findByIdAndEmail(String id);

    Optional<Account> findByEmail(String email);

    Void deleteByEmail(String email);



}