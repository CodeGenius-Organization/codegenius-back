ALTER TABLE course_languages
ADD CONSTRAINT course_languages_constraint_fk_course
FOREIGN KEY (course_fk) REFERENCES course (course_id);

ALTER TABLE course_languages
ADD CONSTRAINT course_languages_constraint_fk_language
FOREIGN KEY (language_fk) REFERENCES language (language_id);