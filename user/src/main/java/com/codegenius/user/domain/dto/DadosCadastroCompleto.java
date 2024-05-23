package com.codegenius.user.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

/**
 * Data class representing complete user registration data.
 *
 * @author hidek
 * @since 2023-08-09
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DadosCadastroCompleto {
    @JsonProperty("id")
    private UUID id;
    @JsonProperty("nome")
    private String name;
    @JsonProperty("email")
    private String email;
    @JsonProperty("password")
    private String password;
    @JsonProperty("ativo")
    private Boolean active;
}
