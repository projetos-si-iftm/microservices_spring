package com.authentication.microservice_authentication.application.port.in;


import java.util.List;
import java.util.Optional;

import com.authentication.microservice_authentication.application.dto.AccountNameAndPhoto;
import com.authentication.microservice_authentication.application.dto.LoginRequest;
import com.authentication.microservice_authentication.domain.model.Account;
import com.authentication.microservice_authentication.domain.model.VerifiedToken;


public interface AccountUseCase {
    Account createOrUpdateAccountFromLogin(LoginRequest loginRequest) throws Exception;
    Optional<Account> getAccountById(String id);
    List<AccountNameAndPhoto> getAllAccounts();
  Optional<VerifiedToken> validateToken(String token);
    
    
    
}