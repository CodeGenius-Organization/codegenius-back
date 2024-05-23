CREATE TABLE lesson_content (
    lesson_content_id BINARY(16) PRIMARY KEY,
    title VARCHAR(45) NOT NULL,
    content VARCHAR(1500) NOT NULL,
    media BLOB,
    module_lesson_fk BINARY(16)
);