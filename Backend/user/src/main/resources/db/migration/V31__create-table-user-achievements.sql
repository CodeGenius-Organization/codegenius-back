CREATE TABLE user_achievements (
    user_achievement_id BINARY(16) PRIMARY KEY,
    user_fk BINARY(16),
    achievement_fk BINARY(16),
    date_achieved DATE,
    is_visible TINYINT
);