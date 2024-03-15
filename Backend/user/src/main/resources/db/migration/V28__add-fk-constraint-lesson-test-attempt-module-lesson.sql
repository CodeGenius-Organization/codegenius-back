ALTER TABLE lesson_test_attempts
ADD CONSTRAINT lta_constraint_fk_module_lesson
FOREIGN KEY (module_lesson_fk) REFERENCES module_lesson (module_lesson_id);