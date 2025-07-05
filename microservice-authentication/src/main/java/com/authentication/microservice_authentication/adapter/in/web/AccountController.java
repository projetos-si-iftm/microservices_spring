package com.authentication.microservice_authentication.adapter.in.web;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.OK;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.authentication.microservice_authentication.application.dto.AccountNameAndPhoto;
import com.authentication.microservice_authentication.application.dto.AccountResponse;
import com.authentication.microservice_authentication.application.dto.LoginRequest;
import com.authentication.microservice_authentication.application.port.in.AccountUseCase;
import com.authentication.microservice_authentication.domain.model.Account;
import com.authentication.microservice_authentication.domain.model.VerifiedToken;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor

public class AccountController {

      private final AccountUseCase accountUseCase;

      @PostMapping("/login/google")
      public ResponseEntity<AccountResponse> loginWithGoogle(@RequestBody LoginRequest loginRequest) throws Exception {
            Account account = accountUseCase.createOrUpdateAccountFromLogin(loginRequest);
            return ResponseEntity.ok(mapToResponse(account));
      }

      @GetMapping("/accounts/{id}")
      public ResponseEntity<AccountResponse> getAccount(@PathVariable String id) {
            return accountUseCase.getAccountById(id)
                        .map(this::mapToResponse)
                        .map(ResponseEntity::ok)
                        .orElse(ResponseEntity.notFound().build());
      }

      @ResponseStatus(OK)
      @GetMapping("/accounts/all")
      public List<AccountNameAndPhoto> getAccounts() {

            return accountUseCase.getAllAccounts();
      }

      @PostMapping("/validate")
      public ResponseEntity<Void> validateRequest(@RequestHeader("Authorization") String authHeader) throws Exception {

            if (authHeader == null || !authHeader.startsWith("Bearer ")) {

                  return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
            String token = authHeader.substring(7);

            Optional<VerifiedToken> validationResult = accountUseCase.validateToken(token);

            if (validationResult.isPresent()) {

                  return ResponseEntity.ok().build();
            } else {
                  log.info("deu erro");
                  return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
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
}
