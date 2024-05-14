USE codegenius;
DESC course;
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
    DECLARE moduleLessonFk BINARY(16);
    DECLARE lessonContentFk BINARY(16);
    DECLARE questionFk BINARY(16);
    
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

        SET c = c + 1;
    END WHILE;

	SELECT course_id
    INTO courseFk
    FROM course
    WHERE title = 'Básicos Java';

	INSERT INTO course_module (course_module_id, module_name, module_order, course_fk) VALUES
	(UUID_TO_BIN(UUID()), 'Variáveis', 1, courseFk),
	(UUID_TO_BIN(UUID()), 'Funções', 2, courseFk),
	(UUID_TO_BIN(UUID()), 'Classes', 3, courseFk);
	-- --------------------------------------------------------------------------------------------------------------------
	/* INSERT DAS LIÇÕES PARA MÓDULOS COM NOME X */
	SELECT A.course_module_id
	INTO moduleFk 
	FROM course_module A
	JOIN course B
		ON B.course_id = A.course_fk
	WHERE A.course_fk = courseFk
    AND A.module_name = 'Variáveis'
	LIMIT 1;

	INSERT INTO module_lesson (module_lesson_id, lesson_title, lesson_order, content_description, course_module_fk) VALUES
	(UUID_TO_BIN(UUID()), CONCAT('Lição ', 1, ' teste'), 1, 'Teste descrição de conteúdo', moduleFk);
    
    SELECT A.course_module_id
	INTO moduleFk 
	FROM course_module A
	JOIN course B
		ON B.course_id = A.course_fk
	WHERE A.course_fk = courseFk
    AND A.module_name = 'Funções'
	LIMIT 1;
    
	INSERT INTO module_lesson (module_lesson_id, lesson_title, lesson_order, content_description, course_module_fk) VALUES
	(UUID_TO_BIN(UUID()), CONCAT('Lição ', 1, ' teste'), 1, 'Teste descrição de conteúdo', moduleFk);

	SELECT A.course_module_id
	INTO moduleFk 
	FROM course_module A
	JOIN course B
		ON B.course_id = A.course_fk
	WHERE A.course_fk = courseFk
    AND A.module_name = 'Classes'
	LIMIT 1;
    
	INSERT INTO module_lesson (module_lesson_id, lesson_title, lesson_order, content_description, course_module_fk) VALUES
	(UUID_TO_BIN(UUID()), CONCAT('Lição ', 1, ' teste'), 1, 'Teste descrição de conteúdo', moduleFk);
	/* INSERT DAS LIÇÕES PARA MÓDULOS COM NOME X */
	-- ------------------------------------------------------------------------------------------------
	/* INSERT DO CONTEÚDO DA LIÇÃO, QUESTÕES E RESPOSTAS PRA UMA LIÇÃO DE MÓDULO COM NOME X */
    SELECT module_lesson_id
	INTO moduleLessonFk 
    FROM module_lesson A 
    JOIN course_module B
		ON B.course_module_id = A.course_module_fk
	WHERE b.course_fk = courseFk
    AND B.module_name = 'Variáveis'
    LIMIT 1;
        
    INSERT INTO lesson_content (lesson_content_id, title, content, module_lesson_fk) VALUES
    (UUID_TO_BIN(UUID()), 'Título da lição', 'Conteúdo da lição', moduleLessonFk);
    
    SELECT lesson_content_id INTO lessonContentFk FROM lesson_content LIMIT 1;
    
    INSERT INTO question (id_question, question_type, statement, test_question, lesson_content_fk) VALUES
    (UUID_TO_BIN(UUID()), 'Alternativa', 'Enunciado de uma pergunta 1', 1, lessonContentFk),
    (UUID_TO_BIN(UUID()), 'Alternativa', 'Enunciado de uma pergunta 2', 1, lessonContentFk),
    (UUID_TO_BIN(UUID()), 'Alternativa', 'Enunciado de uma pergunta 3', 1, lessonContentFk),
    (UUID_TO_BIN(UUID()), 'Alternativa', 'Enunciado de uma pergunta 4', 1, lessonContentFk),
    (UUID_TO_BIN(UUID()), 'Alternativa', 'Enunciado de uma pergunta 5', 1, lessonContentFk);
    
    SELECT
		id_question
	INTO questionFk
    FROM question
    LIMIT 1;
    
    INSERT INTO response (id_response, answer, correct, explanation, fk_question) VALUES
	(UUID_TO_BIN(UUID()), 'Resposta 1', 1, 'Resposta correta 1', questionFk),
	(UUID_TO_BIN(UUID()), 'Resposta 1', 0, 'Resposta correta 2', questionFk),
	(UUID_TO_BIN(UUID()), 'Resposta 1', 0, 'Resposta correta 3', questionFk),
	(UUID_TO_BIN(UUID()), 'Resposta 1', 0, 'Resposta correta 4', questionFk),
	(UUID_TO_BIN(UUID()), 'Resposta 1', 0, 'Resposta correta 5', questionFk);

	SELECT
		id_question
	INTO questionFk
    FROM question
    LIMIT 1 OFFSET 1;
    
    INSERT INTO response (id_response, answer, correct, explanation, fk_question) VALUES
	(UUID_TO_BIN(UUID()), 'Resposta 1', 1, 'Resposta correta 1', questionFk),
	(UUID_TO_BIN(UUID()), 'Resposta 1', 0, 'Resposta correta 2', questionFk),
	(UUID_TO_BIN(UUID()), 'Resposta 1', 0, 'Resposta correta 3', questionFk),
	(UUID_TO_BIN(UUID()), 'Resposta 1', 0, 'Resposta correta 4', questionFk),
	(UUID_TO_BIN(UUID()), 'Resposta 1', 0, 'Resposta correta 5', questionFk);

	SELECT
		id_question
	INTO questionFk
    FROM question
    LIMIT 1 OFFSET 2;
    
    INSERT INTO response (id_response, answer, correct, explanation, fk_question) VALUES
	(UUID_TO_BIN(UUID()), 'Resposta 1', 1, 'Resposta correta 1', questionFk),
	(UUID_TO_BIN(UUID()), 'Resposta 1', 0, 'Resposta correta 2', questionFk),
	(UUID_TO_BIN(UUID()), 'Resposta 1', 0, 'Resposta correta 3', questionFk),
	(UUID_TO_BIN(UUID()), 'Resposta 1', 0, 'Resposta correta 4', questionFk),
	(UUID_TO_BIN(UUID()), 'Resposta 1', 0, 'Resposta correta 5', questionFk);
    
    SELECT
		id_question
	INTO questionFk
    FROM question
    LIMIT 1 OFFSET 3;
    
    INSERT INTO response (id_response, answer, correct, explanation, fk_question) VALUES
	(UUID_TO_BIN(UUID()), 'Resposta 1', 1, 'Resposta correta 1', questionFk),
	(UUID_TO_BIN(UUID()), 'Resposta 1', 0, 'Resposta correta 2', questionFk),
	(UUID_TO_BIN(UUID()), 'Resposta 1', 0, 'Resposta correta 3', questionFk),
	(UUID_TO_BIN(UUID()), 'Resposta 1', 0, 'Resposta correta 4', questionFk),
	(UUID_TO_BIN(UUID()), 'Resposta 1', 0, 'Resposta correta 5', questionFk);
    
    SELECT
		id_question
	INTO questionFk
    FROM question
    LIMIT 1 OFFSET 4;
    
    INSERT INTO response (id_response, answer, correct, explanation, fk_question) VALUES
	(UUID_TO_BIN(UUID()), 'Resposta 1', 1, 'Resposta correta 1', questionFk),
	(UUID_TO_BIN(UUID()), 'Resposta 1', 0, 'Resposta correta 2', questionFk),
	(UUID_TO_BIN(UUID()), 'Resposta 1', 0, 'Resposta correta 3', questionFk),
	(UUID_TO_BIN(UUID()), 'Resposta 1', 0, 'Resposta correta 4', questionFk),
	(UUID_TO_BIN(UUID()), 'Resposta 1', 0, 'Resposta correta 5', questionFk);

	SELECT
		id_question
	INTO questionFk
    FROM question
    LIMIT 1 OFFSET 5;
    
    INSERT INTO response (id_response, answer, correct, explanation, fk_question) VALUES
	(UUID_TO_BIN(UUID()), 'Resposta 1', 1, 'Resposta correta 1', questionFk),
	(UUID_TO_BIN(UUID()), 'Resposta 1', 0, 'Resposta correta 2', questionFk),
	(UUID_TO_BIN(UUID()), 'Resposta 1', 0, 'Resposta correta 3', questionFk),
	(UUID_TO_BIN(UUID()), 'Resposta 1', 0, 'Resposta correta 4', questionFk),
	(UUID_TO_BIN(UUID()), 'Resposta 1', 0, 'Resposta correta 5', questionFk);
    
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