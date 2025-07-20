package com.authentication.microservice_authentication.domain.model;

import java.time.LocalDateTime;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// Esta é uma classe POJO (Plain Old Java Object).
// Não tem anotações de framework. É pura.
// Usei Lombok para brevidade, mas você poderia gerar getters/setters manualmente.
@Data // @Getter, @Setter, @ToString, @EqualsAndHashCode, @RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Account {

    private String id; 
    private String name;
    private String email;
    private String photo;
    private AccountRole role;
    private Boolean active;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}