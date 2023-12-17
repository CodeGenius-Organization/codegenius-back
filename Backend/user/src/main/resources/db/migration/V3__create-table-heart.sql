CREATE TABLE user_hearts (
    id_hearts binary(16) PRIMARY KEY,
    hearts INT NOT NULL,
    last_update DATETIME NOT NULL,
    fk_User binary(16) NOT NULL
)