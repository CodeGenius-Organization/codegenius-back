package com.codegenius.user.domain.service;

import com.codegenius.user.domain.dto.DadosCoracaoUser;
import com.codegenius.user.domain.dto.DadosCoracaoUserCompleto;
import com.codegenius.user.domain.model.HeartModel;
import com.codegenius.user.domain.model.UserModel;
import com.codegenius.user.domain.repository.HeartRepository;
import com.codegenius.user.domain.repository.UserRepository;
import com.codegenius.user.infra.exception.GlobalExceptionHandler;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Service class for managing heart-related game data.
 *
 * @author hidek
 * @since 2023-10-08
 */
@Service
@Transactional
public class HeartServiceImpl implements HeartService{

    private final HeartRepository heartRepository;
    private final UserRepository userRepository;

    @Autowired
    public HeartServiceImpl(HeartRepository heartRepository, UserRepository userRepository) {
        this.heartRepository = heartRepository;
        this.userRepository = userRepository;
    }

    /**
     * Creates a new heart entry for a user.
     *
     * @param fkUser                                    The heart-related information to be created.
     * @return                                            The complete heart-related information for the newly created game.
     * @throws GlobalExceptionHandler.BadRequestException If the user's UUID is already associated with another game.
     *
     * @author hidek
     * @since 2023-10-09
     */
    @Transactional
    @Override
    public DadosCoracaoUserCompleto createHeart(UUID fkUser) {
        UserModel userModel = userRepository.findById(fkUser)
                .orElseThrow();

        HeartModel heartModel = new HeartModel();

        if (isFkUserAlreadyUsed(userModel)) {
            throw new GlobalExceptionHandler.BadRequestException("User already used");
        }

        heartModel.setHearts(3);
        heartModel.setFkUser(userModel);
        heartModel.setLastUpdate(LocalDateTime.now());

        HeartModel createdHeart = heartRepository.save(heartModel);
        return convertToDTOCompleto(createdHeart);
    }

    /**
     * Retrieves simplified heart-related information for a user based on their UUID.
     *
     * @param fkUser                                    The UUID of the user.
     * @return                                          The simplified heart-related information for the user.
     * @throws GlobalExceptionHandler.NotFoundException If the user is not found.
     *
     * @author hidek
     * @since 2023-10-09
     */
    @Override
    public DadosCoracaoUser getHeartByFkUser(UUID fkUser) {
        UserModel userModel = userRepository.findById(fkUser)
                .orElseThrow();

        HeartModel hearts = heartRepository.findByFkUser(userModel);
        if (hearts != null) {
            return convertToDTOSimplified(hearts);
        } else {
            throw new GlobalExceptionHandler.NotFoundException("User not found");
        }
    }

    /**
     * Updates heart-related information for a user based on their UUID.
     *
     * @param fkUser                                    The UUID of the user.
     * @param heartDTO                                  The updated heart-related information.
     * @return                                          The updated heart-related information.
     * @throws GlobalExceptionHandler.NotFoundException If the user is not found.
     *
     * @author hidek
     * @since 2023-10-09
     */
    @Transactional
    @Override
    public DadosCoracaoUser updateHeartByFkUser(UUID fkUser, DadosCoracaoUser heartDTO) {
        UserModel userModel = userRepository.findById(fkUser)
                .orElseThrow();

        HeartModel existingHeart = heartRepository.findByFkUser(userModel);

        if (existingHeart != null) {
            existingHeart.setHearts(heartDTO.getHearts());
            existingHeart.setLastUpdate(LocalDateTime.now());
            heartRepository.save(existingHeart);

            DadosCoracaoUser updatedHeartDTO = convertToDTOSimplified(existingHeart);

            return updatedHeartDTO;
        } else {
            throw new GlobalExceptionHandler.NotFoundException("User not found");
        }
    }

    /**
     * Converts a HeartModel entity to simplified heart-related information (DTO).
     *
     * @param heart The HeartModel entity.
     * @return      The simplified heart-related information.
     *
     * @author hidek
     * @since 2023-10-09
     */
    public DadosCoracaoUser convertToDTOSimplified(HeartModel heart) {
        return new DadosCoracaoUser(
                heart.getHearts(),
                heart.getLastUpdate(),
                heart.getFkUser().getId()
        );
    }

    /**
     * Checks if a user's UUID is already associated with a heart.
     *
     * @param fkUser The UUID of the user.
     * @return       True if the user's UUID is already associated with a heart, false otherwise.
     *
     * @author hidek
     * @since 2023-10-09
     */
    private boolean isFkUserAlreadyUsed(UserModel fkUser) {
        HeartModel heartsWithSameFkUser = heartRepository.findByFkUser(fkUser);
        return heartsWithSameFkUser != null;
    }

    /**
     * Converts a HeartModel entity to complete heart-related information (DTO).
     *
     * @param heart The HeartModel entity.
     * @return     The complete heart-related information.
     *
     * @author hidek
     * @since 2023-10-09
     */
    private DadosCoracaoUserCompleto convertToDTOCompleto(HeartModel heart) {
        return new DadosCoracaoUserCompleto(
                heart.getId(),
                heart.getHearts(),
                heart.getLastUpdate(),
                heart.getFkUser().getId()
        );
    }

    /**
     * Converts a DadosCoracaoUser DTO to a HeartModel entity.
     *
     * @param heartDTO The heart-related information DTO.
     * @return        The HeartModel entity.
     *
     * @author hidek
     * @since 2023-10-09
     */
    private HeartModel convertToEntity(DadosCoracaoUser heartDTO) {
        UUID id = heartDTO.getFkUser();
        UserModel userModel = userRepository.findById(id)
                .orElseThrow(() -> new GlobalExceptionHandler.NotFoundException("User not found"));

        HeartModel heartModel = new HeartModel();
        heartModel.setHearts(heartDTO.getHearts());
        heartModel.setLastUpdate(LocalDateTime.now());
        heartModel.setFkUser(userModel);

        return heartModel;
    }
}
