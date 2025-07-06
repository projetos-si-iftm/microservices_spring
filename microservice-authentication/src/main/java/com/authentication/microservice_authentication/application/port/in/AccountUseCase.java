package com.authentication.microservice_authentication.application.port.in;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.authentication.microservice_authentication.application.dto.AccountNameAndPhoto;
import com.authentication.microservice_authentication.application.dto.AccountResponse;
import com.authentication.microservice_authentication.application.dto.LoginRequest;
import com.authentication.microservice_authentication.domain.model.VerifiedToken;

public interface AccountUseCase {
  
  ResponseEntity<AccountResponse> createOrUpdateAccountFromLogin(LoginRequest loginRequest);

  AccountResponse getAccountById(String id);

  List<AccountNameAndPhoto> getAllAccounts();

  Optional<VerifiedToken> validateToken(String token);

}