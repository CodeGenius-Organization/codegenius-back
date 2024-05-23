CREATE TABLE module_feedback (
    module_feedback_id BINARY(16) PRIMARY KEY,
    stars INT NOT NULL,
    feedback VARCHAR(1500),
    feedback_date DATE NOT NULL,
    module_fk BINARY(16),
    user_fk BINARY(16)
);