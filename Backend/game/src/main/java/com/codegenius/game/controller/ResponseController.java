package com.codegenius.game.controller;


import com.codegenius.game.domain.dto.*;
import com.codegenius.game.domain.service.ResponseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/responses")
@SecurityRequirement(name = "Bearer Authentication")
@CrossOrigin(origins = "*")
public class ResponseController {

    private final ResponseService responseService;

    @Autowired
    public ResponseController(ResponseService responseService) { this.responseService = responseService; }

    @PostMapping
    @Operation(summary = "Create a new response", description = "Endpoint to create a new response with provided details.")
    @ApiResponse(responseCode = "201", description = "Response created", content = @Content(mediaType = "application/json", schema = @Schema(implementation = DadosRespostasCompleto.class)))
    public ResponseEntity<DadosRespostasCompleto> createQuestion (
            @RequestBody @Valid DadosRespostas responseDTO,
            UriComponentsBuilder uriBuilder){
        DadosRespostasCompleto createdResponse = responseService.createResponse(responseDTO);

        var uri = uriBuilder.path("/responses/{id}").buildAndExpand(createdResponse.getId()).toUri();
        return ResponseEntity.created(uri).body(createdResponse);
    }

    @GetMapping("/{fkQuestion}")
    @Operation(summary = "Get all responses", description = "Endpoint to retrieve details of all available responses.")
    @ApiResponse(responseCode = "200", description = "List of responses", content = @Content(mediaType = "application/json", schema = @Schema(implementation = DadosRespostasCompleto.class)))
    public ResponseEntity<List<DadosRespostasCompleto>> getResponse (@PathVariable UUID fkQuestion){
        List<DadosRespostasCompleto> respostas = responseService.findAll(fkQuestion);
        return respostas.isEmpty()
                ? ResponseEntity.status(204).body(respostas)
                : ResponseEntity.status(200).body(respostas);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a reasponse", description = "Endpoint to update details of a specific response.")
    @ApiResponse(responseCode = "200", description = "Updated response", content = @Content(mediaType = "application/json", schema = @Schema(implementation = DadosRespostas.class)))
    public ResponseEntity<DadosRespostas> putResponse (
            @RequestParam UUID id,
            @RequestBody @Valid DadosRespostasUpdate respostaDTO
    ) {
        DadosRespostas resposta = responseService.update(id, respostaDTO);
        return ResponseEntity.status(200).body(resposta);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a response", description = "Endpoint to delete details of a specific response.")
    @ApiResponse(responseCode = "204", description = "Response deleted")
    public ResponseEntity deleteResponse (
            @PathVariable UUID id
    ) {
        responseService.delete(id);
        return ResponseEntity.status(204).build();
    }

    @DeleteMapping("/question/{fkQuestion}")
    @Operation(summary = "Delete all response by question id", description = "Endpoint to delete details of all response by specific question id.")
    @ApiResponse(responseCode = "204", description = "Response deleted")
    public ResponseEntity deleteAllResponse (
            @PathVariable UUID id
    ) {
        responseService.deleteAll(id);
        return ResponseEntity.status(204).build();
    }
}
