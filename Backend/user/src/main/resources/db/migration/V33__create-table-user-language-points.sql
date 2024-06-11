CREATE TABLE user_language_points (
    user_language_points_id BINARY(16) PRIMARY KEY,
    points INT,
    user_fk BINARY(16),
    language_fk BINARY(16)
)