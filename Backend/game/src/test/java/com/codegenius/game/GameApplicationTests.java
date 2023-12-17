package com.codegenius.game;

import com.codegenius.game.domain.dto.DadosQuestoes;
import com.codegenius.game.domain.dto.DadosQuestoesCompleto;
import com.codegenius.game.domain.model.QuestionModel;
import com.codegenius.game.domain.repository.QuestionRepository;
import com.codegenius.game.domain.service.QuestionService;
import com.codegenius.game.domain.utils.Fila;
import com.codegenius.game.infra.exception.GlobalExceptionHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RunWith(SpringRunner.class)
@DataJpaTest
class GameApplicationTests {

	@Mock
	private QuestionRepository questionRepository;

	@InjectMocks
	private QuestionService questionService;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	@DisplayName(value = "Testando a criação de questão com valores validos")
	public void testCreateQuestion_Success() {
		UUID questionId = UUID.randomUUID();
		UUID lessonId = UUID.randomUUID();
		DadosQuestoes questoesDTO = new DadosQuestoes("type", "statement", true, lessonId);
		QuestionModel questionModel = new QuestionModel(questionId, questoesDTO.getQuestion_type(), questoesDTO.getStatement(), questoesDTO.getTest_question(), questoesDTO.getLesson_content());

		when(questionRepository.save(any(QuestionModel.class))).thenReturn(questionModel);

		DadosQuestoesCompleto savedQuestion = questionService.createQuestion(questoesDTO);

		assertEquals("type", savedQuestion.getQuestion_type());
		assertEquals("statement", savedQuestion.getStatement());
		assertEquals(true, savedQuestion.getTest_question());
		assertEquals(lessonId, savedQuestion.getLesson_content());
	}

}
