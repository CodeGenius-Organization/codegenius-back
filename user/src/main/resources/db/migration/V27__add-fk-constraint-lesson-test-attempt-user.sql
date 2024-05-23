ALTER TABLE lesson_test_attempts
ADD CONSTRAINT lta_constraint_fk_user
FOREIGN KEY (user_fk) REFERENCES _user (id_user);