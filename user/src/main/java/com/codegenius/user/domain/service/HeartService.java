package com.codegenius.user.domain.service;

import com.codegenius.user.domain.dto.DadosCoracaoUser;
import com.codegenius.user.domain.dto.DadosCoracaoUserCompleto;

import java.util.UUID;

public interface HeartService {
    /**
     * Creates a new heart entry for a user.
     *
     * @param fkUser The heart-related information to be created.
     * @return The complete heart-related information for the newly created heart.
     */
    DadosCoracaoUserCompleto createHeart(UUID fkUser);

    /**
     * Retrieves simplified heart-related information for a user based on their UUID.
     *
     * @param fkUser The UUID of the user.
     * @return The simplified heart-related information for the user.
     */
    DadosCoracaoUser getHeartByFkUser(UUID fkUser);

    /**
     * Updates heart-related information for a user based on their UUID.
     *
     * @param fkUser The UUID of the user.
     * @param heartDTO The updated heart-related information.
     * @return The updated heart-related information.
     */
    DadosCoracaoUser updateHeartByFkUser(UUID fkUser, DadosCoracaoUser heartDTO);
}
