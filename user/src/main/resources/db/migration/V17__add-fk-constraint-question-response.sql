ALTER TABLE response
ADD CONSTRAINT constraint_fk_question_response
FOREIGN KEY (fk_question) REFERENCES question (id_question);