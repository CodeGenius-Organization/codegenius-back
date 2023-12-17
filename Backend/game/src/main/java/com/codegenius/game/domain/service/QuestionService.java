package com.codegenius.game.domain.service;

import com.codegenius.game.domain.dto.*;
import com.codegenius.game.domain.model.QuestionModel;
import com.codegenius.game.domain.repository.QuestionRepository;
import com.codegenius.game.domain.utils.Fila;
import com.codegenius.game.domain.utils.GerenciadorArquivoTxt;
import com.codegenius.game.infra.exception.GlobalExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * Service class for managing question-related game data.
 *
 * @author hidek
 * @since 2023-10-08
 */
@Service
public class QuestionService {
    private final QuestionRepository questionRepository;

    private final ResponseService responseService;

    @Autowired
    public QuestionService(QuestionRepository questionRepository, ResponseService responseService) {
        this.questionRepository = questionRepository;
        this.responseService = responseService;
    }

    public DadosQuestoesCompleto createQuestion(DadosQuestoes questionDTO) {
        QuestionModel question = new QuestionModel(null, questionDTO.getQuestion_type(), questionDTO.getStatement(), questionDTO.getTest_question(), questionDTO.getLesson_content());

        questionRepository.save(question);

        DadosQuestoesCompleto questionComp = new DadosQuestoesCompleto(question.getId(), question.getQuestionType(), question.getStatement(), question.getTestQuestion(), question.getLeassonContent());
        return questionComp;
    }

    public List<DadosQuestoesCompleto> findAll() {
        List<QuestionModel> questoes = questionRepository.findAll();

        List<DadosQuestoesCompleto> questoesDTO = convertToQuestaoModelList(questoes);

        return questoesDTO;
    }

    public DadosQuestoes update(UUID id, DadosQuestoesUpdate questaoDTO) {
        QuestionModel questao = questionRepository.findById(id).orElseThrow(() -> new GlobalExceptionHandler.NotFoundException("Question not found with id: " + id));

        if (questaoDTO.getQuestion_type() != null) {
            questao.setQuestionType(questaoDTO.getQuestion_type());
        }
        if (questaoDTO.getTest_question() != null) {
            questao.setTestQuestion(questaoDTO.getTest_question());
        }
        if (questaoDTO.getStatement() != null) {
            questao.setStatement(questaoDTO.getStatement());
        }
        if (questaoDTO.getLesson_content() != null) {
            questao.setLeassonContent(questaoDTO.getLesson_content());
        }

        QuestionModel updatedQuestao = questionRepository.save(questao);

        DadosQuestoes updatedQuestaoDTO = convertToDTO(updatedQuestao);

        return updatedQuestaoDTO;
    }

    public void delete(UUID id) {
        QuestionModel questao = questionRepository.findById(id).orElseThrow(() -> new GlobalExceptionHandler.NotFoundException("Question not found with id: " + id));

        responseService.deleteAll(questao.getId());
        questionRepository.deleteById(questao.getId());
    }

    public List<DadosQuestaoTxtDTO> gravarTxt(String nomeArq) {
        GerenciadorArquivoTxt gerar = new GerenciadorArquivoTxt();

        return gerar.leArquivoTxt(nomeArq, questionRepository);
    }

    public List<DadosQuestaoTxtDTO> baixarTxt(String nomeArq) {
        GerenciadorArquivoTxt gerar = new GerenciadorArquivoTxt();
        List<DadosQuestaoTxtDTO> dto = convertToQuestaoModelListTxt(questionRepository.findAll());

        return gerar.gravaArquivoTxt(dto, nomeArq);
    }

    public Fila<DadosQuestoesCompleto> fila() {
        Random random = new Random();
        List<QuestionModel> questoes = questionRepository.findAll();
        Fila<DadosQuestoesCompleto> data = new Fila<>(5);

        if (questoes.size() - 1 < 5) {
            throw new GlobalExceptionHandler.NotFoundException("insufficient quantity of questions");
        }

        List<Integer> aux = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            boolean b = true;

            int n = random.nextInt(questoes.size());

            for (int j = 0; j < aux.size(); j++) {
                if (n == aux.get(j)) {
                    b = false;
                }
            }

            if (b) {
                aux.add(n);
                data.insert(convertToDTOCompleto(questoes.get(n)));
            } else {
                i--;
            }
        }

        return data;
    }
    private List<DadosQuestaoTxtDTO> convertToQuestaoModelListTxt(List<QuestionModel> questoes) {
        List<DadosQuestaoTxtDTO> questao = new ArrayList<>();

        for (QuestionModel questaoModel: questoes) {
            Random random = new Random();
            int num = random.nextInt(2);
            DadosQuestaoTxtDTO questaoDTO = new DadosQuestaoTxtDTO(questaoModel.getQuestionType(), questaoModel.getStatement(), questaoModel.getTestQuestion(), questaoModel.getLeassonContent(), num == 0 ? "Fácil" : "Difícil");
            questao.add(questaoDTO);
        }

        return questao;
    }
    private List<DadosQuestoesCompleto> convertToQuestaoModelList(List<QuestionModel> questoes) {
        List<DadosQuestoesCompleto> questao = new ArrayList<>();

        for (QuestionModel questaoModel: questoes) {
            DadosQuestoesCompleto questaoDTO = new DadosQuestoesCompleto(questaoModel);
            questao.add(questaoDTO);
        }

        return questao;
    }

    private DadosQuestoes convertToDTO(QuestionModel questao) {
        DadosQuestoes questaoDTO = new DadosQuestoes();
        questaoDTO.setQuestion_type(questao.getQuestionType());
        questaoDTO.setTest_question(questao.getTestQuestion());
        questaoDTO.setStatement(questao.getStatement());
        questaoDTO.setLesson_content(questao.getLeassonContent());

        return questaoDTO;
    }

    private DadosQuestoesCompleto convertToDTOCompleto(QuestionModel questao) {
        DadosQuestoesCompleto questoeDTO = new DadosQuestoesCompleto();

        questoeDTO.setId(questao.getId());
        questoeDTO.setQuestion_type(questao.getQuestionType());
        questoeDTO.setTest_question(questao.getTestQuestion());
        questoeDTO.setStatement(questao.getStatement());
        questoeDTO.setLesson_content(questao.getLeassonContent());

        return questoeDTO;
    }
}
