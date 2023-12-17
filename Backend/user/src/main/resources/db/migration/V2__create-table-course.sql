CREATE TABLE course (
    course_id binary(16) PRIMARY KEY,
    title VARCHAR(45) NOT NULL,
    course_description VARCHAR(100) NOT NULL,
    content_description VARCHAR(100) NOT NULL,
    image MEDIUMBLOB,
    available BOOLEAN NOT NULL DEFAULT false
);