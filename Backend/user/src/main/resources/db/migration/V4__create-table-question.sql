CREATE TABLE question (
    id_question binary(16) PRIMARY KEY,
    question_type VARCHAR(50) NOT NULL,
    statement VARCHAR(250) NOT NULL,
    test_question BOOLEAN NOT NULL,
    lesson_content_fk binary(16) NOT NULL
)