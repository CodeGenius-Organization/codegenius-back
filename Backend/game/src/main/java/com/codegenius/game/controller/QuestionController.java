package com.codegenius.game.controller;

import com.codegenius.game.domain.dto.*;
import com.codegenius.game.domain.utils.Fila;
import com.codegenius.game.domain.service.QuestionService;
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
@RequestMapping("/questions")
@SecurityRequirement(name = "Bearer Authentication")
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:8383"})
public class QuestionController {

    private final QuestionService questionService;

    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @PostMapping
    @Operation(summary = "Create a new question", description = "Endpoint to create a new question with provided details.")
    @ApiResponse(responseCode = "201", description = "Question created", content = @Content(mediaType = "application/json", schema = @Schema(implementation = DadosQuestoesCompleto.class)))
    public ResponseEntity<DadosQuestoesCompleto> createQuestion (
            @RequestBody @Valid DadosQuestoes questionDTO,
            UriComponentsBuilder uriBuilder){
       DadosQuestoesCompleto createdQuestion = questionService.createQuestion(questionDTO);

        var uri = uriBuilder.path("/questions/{id}").buildAndExpand(createdQuestion.getId()).toUri();
        return ResponseEntity.created(uri).body(createdQuestion);
    }

    @GetMapping
    @Operation(summary = "Get all questions", description = "Endpoint to retrieve details of all available questions.")
    @ApiResponse(responseCode = "200", description = "List of questions", content = @Content(mediaType = "application/json", schema = @Schema(implementation = DadosQuestoesCompleto.class)))
    public ResponseEntity<List<DadosQuestoesCompleto>> getQuestion (){
        List<DadosQuestoesCompleto> questoes = questionService.findAll();
        return questoes.isEmpty()
                ? ResponseEntity.status(204).body(questoes)
                : ResponseEntity.status(200).body(questoes);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a question", description = "Endpoint to update details of a specific question.")
    @ApiResponse(responseCode = "200", description = "Updated question", content = @Content(mediaType = "application/json", schema = @Schema(implementation = DadosQuestoes.class)))
    public ResponseEntity<DadosQuestoes> putQuestion (
            @PathVariable UUID id,
            @RequestBody @Valid DadosQuestoesUpdate questionDTO
    ) {
        DadosQuestoes questao = questionService.update(id,questionDTO);
        return ResponseEntity.status(200).body(questao);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a question", description = "Endpoint to delete details of a specific question.")
    @ApiResponse(responseCode = "204", description = "Question deleted")
    public ResponseEntity deleteQuestion (
            @PathVariable UUID id
    ) {
        questionService.delete(id);
        return ResponseEntity.status(204).build();
    }

    @GetMapping("/fila")
    public ResponseEntity<Fila<DadosQuestoesCompleto>> filaQuestions() {
        Fila<DadosQuestoesCompleto> questoes = questionService.fila();
        return questoes.isEmpty()
                ? ResponseEntity.status(204).build()
                : ResponseEntity.status(200).body(questoes);
    }

    @PostMapping("/gravar/txt/{nomeArq}")
    public ResponseEntity<List<DadosQuestaoTxtDTO>> gravarTxt(@PathVariable String nomeArq){
        List<DadosQuestaoTxtDTO> lista = questionService.gravarTxt(nomeArq);
        return lista.isEmpty()
                ? ResponseEntity.status(204).build()
                : ResponseEntity.status(200).body(lista);
    }

    @GetMapping("/baixar/txt/{nomeArq}")
    public ResponseEntity<List<DadosQuestaoTxtDTO>> baixarTxt(@PathVariable String nomeArq) {
        List<DadosQuestaoTxtDTO> lista = questionService.baixarTxt(nomeArq);
        return lista.isEmpty()
                ? ResponseEntity.status(204).build()
                : ResponseEntity.status(200).body(lista);
    }

    @GetMapping("/exercicios/{lessonContent}")
    public ResponseEntity<List<ListaQuestao>> listQuestion(@PathVariable UUID lessonContent) {
        List<ListaQuestao> lista = questionService.listaQuestaos(lessonContent);
        return lista.isEmpty()
                ? ResponseEntity.status(204).build()
                : ResponseEntity.status(200).body(lista);
    }
}
