CREATE TABLE lesson_test_attempts (
    lesson_test_attempt_id BINARY(16) PRIMARY KEY,
    user_fk BINARY(16),
    module_lesson_fk BINARY(16),
    score INT,
    attempt_start_date DATE,
    attempt_duration VARCHAR(25)
);