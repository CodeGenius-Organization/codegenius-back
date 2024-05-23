ALTER TABLE course_module
ADD CONSTRAINT course_module_constraint_fk_course
FOREIGN KEY (course_fk) REFERENCES course (course_id);