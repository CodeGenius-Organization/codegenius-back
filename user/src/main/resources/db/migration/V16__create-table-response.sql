CREATE TABLE response (
    id_response binary(16) PRIMARY KEY,
    answer VARCHAR(255) NOT NULL,
    correct BOOLEAN NOT NULL DEFAULT false,
    explanation VARCHAR(255) NOT NULL,
    fk_question binary(16) NOT NULL
)