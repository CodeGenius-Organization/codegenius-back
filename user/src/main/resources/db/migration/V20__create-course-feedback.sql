CREATE TABLE course_feedback (
    course_feedback_id BINARY(16) PRIMARY KEY,
    stars INT NOT NULL,
    feedback VARCHAR(1500),
    feedback_date DATE NOT NULL,
    is_read TINYINT NOT NULL,
    course_fk BINARY(16),
    user_fk BINARY(16)
);