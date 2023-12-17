package com.codegenius.user.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Data class representing complete heart-related information for a user.
 *
 * @author hidek
 * @since 2023-10-08
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DadosCoracaoUserCompleto {
    @JsonIgnore
    private UUID id;
    @JsonProperty("coracao")
    private int hearts;
    @JsonProperty("atualizacao")
    private LocalDateTime lastUpdate;
    @JsonProperty("fkUser")
    private UUID fkUser;
}
