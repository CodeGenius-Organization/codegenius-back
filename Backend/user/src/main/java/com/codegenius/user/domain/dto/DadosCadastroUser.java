package com.codegenius.user.domain.dto;

import com.codegenius.user.domain.model.UserModel;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * Data class representing user registration data for response.
 *
 * @author hidek
 * @since 2023-08-09
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DadosCadastroUser {
    @JsonProperty("nome")
    @NotBlank
    private String name;
    @JsonProperty("email")
    @NotBlank
    @Email
    private String email;
    @JsonProperty("password")
    @NotBlank
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[!@#$%^&*])(?=.*[a-z])(?=.*[A-Z]).{8,}$")
    private String password;

    /**
     * Constructor that initializes fields from a UserModel instance.
     *
     * @param user The UserModel instance from which to initialize the fields.
     *
     * @author hidek
     * @since 2023-08-09
     */
    public DadosCadastroUser(UserModel user){
        this.name = user.getName();
        this.email = user.getEmail();
        this.password = user.getPassword();
    }
}
