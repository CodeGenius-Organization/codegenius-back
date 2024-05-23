ALTER TABLE course_feedback
ADD CONSTRAINT course_feedback_constraint_fk_course
FOREIGN KEY (course_fk) REFERENCES course(course_id);