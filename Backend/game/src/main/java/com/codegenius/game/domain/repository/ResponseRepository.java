package com.codegenius.game.domain.repository;

import com.codegenius.game.domain.model.QuestionModel;
import com.codegenius.game.domain.model.ResponseModel;
import com.codegenius.game.domain.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ResponseRepository extends JpaRepository<ResponseModel, UUID> {
    List<ResponseModel> findAllByFkQuestion(QuestionModel fkQuestion);
    void deleteAllByFkQuestion(QuestionModel fkQuestion);
}
