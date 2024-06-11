CREATE TABLE user_category_points (
    user_category_points_id BINARY(16) PRIMARY KEY,
    points INT,
    user_fk BINARY(16),
    category_fk BINARY(16)
)