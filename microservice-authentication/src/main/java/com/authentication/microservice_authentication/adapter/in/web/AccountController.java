package com.authentication.microservice_authentication.adapter.in.web;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.ACCEPTED;

import static org.springframework.http.HttpStatus.OK;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.authentication.microservice_authentication.application.dto.AccountNameAndPhoto;
import com.authentication.microservice_authentication.application.dto.AccountResponse;
import com.authentication.microservice_authentication.application.dto.LoginRequest;
import com.authentication.microservice_authentication.application.port.in.AccountUseCase;
import com.authentication.microservice_authentication.domain.model.Account;

import lombok.RequiredArgsConstructor;

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
      public List<AccountNameAndPhoto>getAccounts(){

            return accountUseCase.getAllAccounts();
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
