package com.authentication.microservice_authentication.application.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.authentication.microservice_authentication.application.dto.LoginRequest;
import com.authentication.microservice_authentication.application.port.in.AccountUseCase;
import com.authentication.microservice_authentication.domain.model.Account;
import com.authentication.microservice_authentication.domain.model.AccountRole;
import com.authentication.microservice_authentication.domain.model.VerifiedToken;
import com.authentication.microservice_authentication.domain.port.in.TokenVerificationPort;
import com.authentication.microservice_authentication.domain.port.out.AccountRepositoryPort;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountUseCase {

  private final AccountRepositoryPort accountRepositoryPort;

  private final TokenVerificationPort tokenVerificationPort;

  LocalDateTime now = LocalDateTime.now();

  @Override

  public Account createOrUpdateAccountFromLogin(LoginRequest loginRequest) {

    VerifiedToken authToken = verifyToken(loginRequest.getIdToken());

    return accountRepositoryPort.findById(authToken.getUid()).map(existingAccount -> {
      existingAccount.setName(authToken.getName());
      existingAccount.setPhoto(authToken.getPicture());
      existingAccount.setUpdatedAt(now);
      return accountRepositoryPort.save(existingAccount);
    }).orElseGet(() -> {
      Account newAccount = Account.builder()
          .id(authToken.getUid())
          .name(authToken.getName())
          .email(authToken.getEmail())
          .photo(authToken.getPicture())
          .role(AccountRole.STUDENT) // < -- verificar essa linha apos concluido
          .active(true)
          .createdAt(LocalDateTime.now())
          .build();
      return accountRepositoryPort.save(newAccount);
    });

  }

  @Override
  public Optional<Account> getAccountById(String id) {

    return accountRepositoryPort.findById(id);
  }

  private VerifiedToken verifyToken(String tokenID) {
    return tokenVerificationPort.verify(tokenID).orElseThrow(() -> new SecurityException("Invalid Token"));
  }

}
