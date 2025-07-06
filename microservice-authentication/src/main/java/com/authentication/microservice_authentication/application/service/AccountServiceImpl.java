package com.authentication.microservice_authentication.application.service;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.authentication.microservice_authentication.application.dto.AccountNameAndPhoto;
import com.authentication.microservice_authentication.application.dto.AccountResponse;
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
  public ResponseEntity<AccountResponse> createOrUpdateAccountFromLogin(LoginRequest loginRequest) {

    VerifiedToken authToken = verifyToken(loginRequest.getIdToken());
    Optional<Account> existingAccount = accountRepositoryPort.findById(authToken.getUid());
    if (existingAccount.isPresent()) {
      return new ResponseEntity<>(mapToResponse(update(authToken, existingAccount.get())), OK);

    }
    return new ResponseEntity<>(mapToResponse(create(authToken)), CREATED);
  }

  @Override
  public AccountResponse getAccountById(String id) {

    return accountRepositoryPort.findById(id).map(this::mapToResponse).get();
  }

  @Override
  public List<AccountNameAndPhoto> getAllAccounts() {
    return accountRepositoryPort.findAll();
  }

  public Optional<VerifiedToken> validateToken(String token) {
    if (token == null || token.isBlank()) {
      return Optional.empty();
    }
    return tokenVerificationPort.verify(token);
  }

  private Account update(VerifiedToken verifiedToken, Account existingAccount) {
    existingAccount.setName(verifiedToken.getName());
    existingAccount.setPhoto(verifiedToken.getPicture());
    existingAccount.setUpdatedAt(now);
    return accountRepositoryPort.save(existingAccount);
  }

  private Account create(VerifiedToken verifiedToken) {
    Account newAccount = Account.builder()
        .id(verifiedToken.getUid())
        .name(verifiedToken.getName())
        .email(verifiedToken.getEmail())
        .photo(verifiedToken.getPicture())
        .role(AccountRole.STUDENT)
        .active(true)
        .createdAt(LocalDateTime.now())
        .build();
    return accountRepositoryPort.save(newAccount);
  }

  private AccountResponse mapToResponse(Account account) {
    return AccountResponse.builder()
        .id(account.getId())
        .name(account.getName())
        .email(account.getEmail())
        .photo(account.getPhoto())
        .role(account.getRole().name())
        .active(account.getActive())
        .build();
  }

  private VerifiedToken verifyToken(String tokenID) {
    return tokenVerificationPort.verify(tokenID).orElseThrow(() -> new SecurityException("Invalid Token"));
  }
}
