package com.codegenius.game.domain.service;

import com.codegenius.game.domain.dto.*;
import com.codegenius.game.domain.model.QuestionModel;
import com.codegenius.game.domain.model.ResponseModel;
import com.codegenius.game.domain.repository.QuestionRepository;
import com.codegenius.game.domain.repository.ResponseRepository;
import com.codegenius.game.infra.exception.GlobalExceptionHandler;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ResponseService {

    private final ResponseRepository repository;

    private final QuestionRepository questionRepository;

    @Autowired
    public ResponseService(ResponseRepository responseRepository, QuestionRepository questionRepository) {
        this.repository = responseRepository;
        this.questionRepository = questionRepository;
    }

    public DadosRespostasCompleto createResponse(DadosRespostas responseDTO) {
        UUID id = responseDTO.getFkQuestion();
        QuestionModel question = questionRepository.findById(id)
                .orElseThrow(() -> new GlobalExceptionHandler.NotFoundException("Question not found by id: " + id));
        ResponseModel response = new ResponseModel(null, responseDTO.getAnswer(), responseDTO.getCorrect(), responseDTO.getExplanation(), question);

        repository.save(response);

        DadosRespostasCompleto responseComp = new DadosRespostasCompleto(response.getId(), response.getAnswer(), response.getCorrect(), response.getExplanation(), response.getFkQuestion().getId());
        return responseComp;
    }

    public List<DadosRespostasCompleto> findAll(UUID fkQuestion) {
        QuestionModel question = questionRepository.findById(fkQuestion)
                .orElseThrow(() -> new GlobalExceptionHandler.NotFoundException("Question not found by id: " + fkQuestion));
        List<ResponseModel> respostas = repository.findAllByFkQuestion(question);

        List<DadosRespostasCompleto> respostasDTO = convertToResponseModelList(respostas);

        return respostasDTO;
    }

    public DadosRespostas update(UUID id, DadosRespostasUpdate respostaDTO) {
        ResponseModel resposta = repository.findById(id).orElseThrow(() -> new GlobalExceptionHandler.NotFoundException("Response not found with id: " + id));

        if (respostaDTO.getAnswer() != null) {
            resposta.setAnswer(respostaDTO.getAnswer());
        }
        if (respostaDTO.getCorrect() != null) {
            resposta.setCorrect(respostaDTO.getCorrect());
        }
        if (respostaDTO.getExplanation() != null) {
            resposta.setExplanation(respostaDTO.getExplanation());
        }
        if (respostaDTO.getFkQuestion() != null) {
            QuestionModel question = questionRepository.findById(respostaDTO.getFkQuestion())
                    .orElseThrow(() -> new GlobalExceptionHandler.NotFoundException("Question not found with id: " + id));
            resposta.setFkQuestion(question);
        }

        ResponseModel updatedResposta = repository.save(resposta);

        DadosRespostas updatedRespostaDTO = convertToDTO(updatedResposta);

        return updatedRespostaDTO;
    }

    public void delete(UUID id) {
        ResponseModel response = repository.findById(id).orElseThrow(() -> new GlobalExceptionHandler.NotFoundException("Response not found with id: " + id));

        repository.deleteById(response.getId());
    }

    @Transactional
    public void deleteAll(UUID id) {
        QuestionModel questao = questionRepository.findById(id).orElseThrow(() -> new GlobalExceptionHandler.NotFoundException("Question not found with id: " + id));

        repository.deleteAllByFkQuestion(questao);
    }

    private List<DadosRespostasCompleto> convertToResponseModelList(List<ResponseModel> respostas) {
        List<DadosRespostasCompleto> resposta = new ArrayList<>();

        for (ResponseModel responseModel: respostas) {
            DadosRespostasCompleto respostaDTO = new DadosRespostasCompleto(responseModel);
            resposta.add(respostaDTO);
        }

        return resposta;
    }

    private DadosRespostas convertToDTO(ResponseModel resposta) {
        DadosRespostas respostaDTO = new DadosRespostas();
        respostaDTO.setAnswer(resposta.getAnswer());
        respostaDTO.setCorrect(resposta.getCorrect());
        respostaDTO.setExplanation(resposta.getExplanation());
        respostaDTO.setFkQuestion(resposta.getFkQuestion().getId());

        return respostaDTO;
    }
}
