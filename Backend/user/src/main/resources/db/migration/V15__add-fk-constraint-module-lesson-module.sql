ALTER TABLE module_lesson
ADD CONSTRAINT module_lesson_constraint_fk_course_module
FOREIGN KEY (course_module_fk) REFERENCES course_module (course_module_id);