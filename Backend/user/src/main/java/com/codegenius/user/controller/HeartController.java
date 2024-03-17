package com.codegenius.user.controller;

import com.codegenius.user.domain.dto.DadosCoracaoUser;
import com.codegenius.user.domain.dto.DadosCoracaoUserCompleto;
import com.codegenius.user.domain.service.HeartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


/**
 * Controller class for handling game-related operations.
 *
 * @author hidek
 * @since 2023-10-08
 */
@RestController
@RequestMapping("/hearts")
@SecurityRequirement(name = "Bearer Authentication")
@CrossOrigin(origins = "*")
public class HeartController {
    private final HeartService heartService;

    @Autowired
    public HeartController(HeartService heartService) {
        this.heartService = heartService;
    }

    /**
     * Endpoint to create a new heart.
     *
     * @param fkUser The heart data to be created.
     * @return ResponseEntity with the created heart and status 201 Created.
     * @author hidek
     * @since 2023-10-08
     */
    @PostMapping
    @Operation(summary = "Create a new heart", description = "Endpoint to create a new heart with provided details.")
    @ApiResponse(responseCode = "201", description = "Heart created", content = @Content(mediaType = "application/json", schema = @Schema(implementation = DadosCoracaoUserCompleto.class)))
    public ResponseEntity<DadosCoracaoUserCompleto> createHeart(
            UUID fkUser) {
        DadosCoracaoUserCompleto createdHeart = heartService.createHeart(fkUser);

        return ResponseEntity.status(201).body(createdHeart);
    }

    /**
     * Endpoint to get simplified information of a heart based on the fkUser.
     *
     * @param fkUser    The fkUser associated with the heart to be retrieved.
     * @return          ResponseEntity with the game information and status 200 OK.
     *
     * @author hidek
     * @since 2023-10-2023
     */
    @GetMapping("/{fkUser}")
    @Operation(summary = "Get heart by fkUser", description = "Endpoint to retrieve heart information by fkUser.")
    @ApiResponse(responseCode = "200", description = "Heart information", content = @Content(mediaType = "application/json", schema = @Schema(implementation = DadosCoracaoUser.class)))
    public ResponseEntity<DadosCoracaoUser> getHeartByFkUser(@PathVariable UUID fkUser) {
        DadosCoracaoUser heart = heartService.getHeartByFkUser(fkUser);
        return ResponseEntity.status(200).body(heart);
    }

    /**
     * Endpoint to update a heart based on the fkUser.
     *
     * @param fkUser    The fkUser associated with the heart to be updated.
     * @param heartDTO  The heart data to be updated.
     * @return          ResponseEntity with the updated heart and status 200 OK.
     *
     * @author hidek
     * @since 2023-10-08
     */
    @PutMapping("/{fkUser}")
    @Operation(summary = "Update heart by fkUser", description = "Endpoint to update heart information by fkUser.")
    @ApiResponse(responseCode = "200", description = "Updated heart", content = @Content(mediaType = "application/json", schema = @Schema(implementation = DadosCoracaoUser.class)))
    public ResponseEntity<DadosCoracaoUser> updateHeartByFkUser(
            @PathVariable UUID fkUser,
            @RequestBody @Valid DadosCoracaoUser heartDTO) {

        DadosCoracaoUser update = heartService.updateHeartByFkUser(fkUser, heartDTO);

        return ResponseEntity.status(200).body(update);
    }
}
