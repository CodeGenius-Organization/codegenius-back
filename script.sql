USE codegenius;

INSERT INTO category (category_id, category) VALUES
(UUID_TO_BIN(UUID()), 'Backend'),
(UUID_TO_BIN(UUID()), 'Mobile'),
(UUID_TO_BIN(UUID()), 'Security'),
(UUID_TO_BIN(UUID()), 'Frontend'),
(UUID_TO_BIN(UUID()), 'Database');

INSERT INTO language (language_id, language) VALUES
(UUID_TO_BIN(UUID()), 'Java'),
(UUID_TO_BIN(UUID()), 'Kotlin'),
(UUID_TO_BIN(UUID()), 'React'),
(UUID_TO_BIN(UUID()), 'Javascript'),
(UUID_TO_BIN(UUID()), 'SQL');

INSERT INTO course (course_id, title, course_description, content_description, image, available) VALUES
(UUID_TO_BIN(UUID()), 'Básicos Kotlin', 'Aprenda os básicos de Kotlin', 'Tipos, variáveis, funções, classes, sintaxe, tudo que é necessário para dominar Kotlin!', 'caminho da imagem', 1),
(UUID_TO_BIN(UUID()), 'Básicos Java', 'Aprenda os básicos de Java', 'Tipos, variáveis, funções, classes, sintaxe, tudo que é necessário para dominar Java!', 'caminho da imagem', 1),
(UUID_TO_BIN(UUID()), 'Básicos React', 'Aprenda os básicos de React', 'Tipos, variáveis, funções, classes, sintaxe, tudo que é necessário para dominar React!', 'caminho da imagem', 1),
(UUID_TO_BIN(UUID()), 'Básicos Javascript', 'Aprenda os básicos de Javascript', 'Tipos, variáveis, funções, classes, sintaxe, tudo que é necessário para dominar Javascript!', 'caminho da imagem', 1),
(UUID_TO_BIN(UUID()), 'Básicos SQL', 'Aprenda os básicos de SQL', 'Tipos, variáveis, funções, classes, sintaxe, tudo que é necessário para dominar SQL!', 'caminho da imagem', 1);

DROP PROCEDURE IF EXISTS insert_data;

DELIMITER $$
CREATE PROCEDURE insert_data()
BEGIN
	DECLARE c INT DEFAULT 0;
    DECLARE c2 INT DEFAULT 0;
    DECLARE courseFk BINARY(16);
    DECLARE languageFk BINARY(16);
    DECLARE categoryFk BINARY(16);
    DECLARE moduleFk BINARY(16);
    
    -- Loop para inserir dados nas tabelas associativas
    WHILE c < 5 DO
        -- Selecionar IDs de curso, linguagem e categoria
        SELECT course_id INTO courseFk FROM course LIMIT 1 OFFSET c;
        SELECT language_id INTO languageFk FROM language LIMIT 1 OFFSET c;
        SELECT category_id INTO categoryFk FROM category LIMIT 1 OFFSET c;

        -- Inserir dados nas tabelas associativas
        INSERT INTO course_languages (course_fk, language_fk) VALUES
        (courseFk, languageFk);

        INSERT INTO course_categories (course_fk, category_fk) VALUES
        (courseFk, categoryFk);

        INSERT INTO course_module (course_module_id, module_name, module_order, course_fk) VALUES
        (UUID_TO_BIN(UUID()), 'Variáveis', 1, courseFk),
        (UUID_TO_BIN(UUID()), 'Funções', 2, courseFk),
        (UUID_TO_BIN(UUID()), 'Classes', 3, courseFk);

        SET c = c + 1;
    END WHILE;

    SET c = 0;

    WHILE c < 3 DO
        SELECT course_module_id INTO moduleFk FROM course_module LIMIT 1 OFFSET c;

        INSERT INTO module_lesson (module_lesson_id, lesson_title, lesson_order, content_description, course_module_fk) VALUES
        (UUID_TO_BIN(UUID()), CONCAT('Lição ', c2 + 1, ' teste'), (c2 + 1), 'Teste descrição de conteúdo', moduleFk);

        SET c = c + 1;
    END WHILE;
END $$
DELIMITER ;

CALL insert_data();

UPDATE course SET teacherFk = UUID_TO_BIN("uuid-do-usuario");

-- SELECT * FROM course_module
-- JOIN course ON course.course_id = course_module.course_fk;

-- SELECT
	-- *
-- FROM module_lesson A
-- JOIN course_module B
	-- ON A.course_module_fk = B.course_module_id
-- JOIN course C
	-- ON B.course_fk = C.course_id;