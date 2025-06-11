package com.authentication.microservice_authentication.application.port.in;


import java.util.Optional;

import com.authentication.microservice_authentication.application.dto.LoginRequest;
import com.authentication.microservice_authentication.domain.model.Account;

// Esta é a porta de entrada. Define as operações (casos de uso)
// que o mundo exterior pode invocar na nossa aplicação. 
public interface AccountUseCase {
    Account createOrUpdateAccountFromLogin(LoginRequest loginRequest) throws Exception;
    Optional<Account> getAccountById(String id);
}