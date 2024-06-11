CREATE TABLE achievements (
    achievements_id BINARY(16) PRIMARY KEY,
    title VARCHAR(150),
    description VARCHAR(250),
    points_needed INT,
    category_fk BINARY(16),
    language_fk BINARY(16)
);