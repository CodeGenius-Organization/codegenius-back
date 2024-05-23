CREATE TABLE _user (
    id_user binary(16) PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL,
    active BOOLEAN NOT NULL DEFAULT true
);