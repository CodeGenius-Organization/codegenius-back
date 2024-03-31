CREATE TABLE course_feedback (
    course_feedback_id BINARY(16) PRIMARY KEY,
    feedback DECIMAL(3,2) NOT NULL,
    feedback_description VARCHAR(1500),
    feedback_date DATE NOT NULL,
    course_fk BINARY(16),
    user_fk BINARY(16)
);