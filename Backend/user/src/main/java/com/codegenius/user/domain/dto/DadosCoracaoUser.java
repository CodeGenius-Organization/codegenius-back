package com.codegenius.user.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Data class representing heart-related information for a user.
 *
 * @author hidek
 * @since 2023-10-08
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DadosCoracaoUser {
    @JsonProperty("coracao")
    @Min(value = 0)
    @Max(value = 3)
    private int hearts;
    @JsonProperty("atualizacao")
    private LocalDateTime lastUpdate;
    @JsonProperty("fkUser")
    private UUID fkUser;
}
