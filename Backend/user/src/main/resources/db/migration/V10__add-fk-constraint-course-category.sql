ALTER TABLE course_categories
ADD CONSTRAINT course_categories_constraint_fk_course
FOREIGN KEY (course_fk) REFERENCES course (course_id);

ALTER TABLE course_categories
ADD CONSTRAINT course_categories_constraint_fk_language
FOREIGN KEY (category_fk) REFERENCES category (category_id);