ALTER TABLE course
ADD CONSTRAINT course_constraint_fk_teacher
FOREIGN KEY (teacher_fk) REFERENCES _user (id_user);