ALTER TABLE user_hearts
ADD CONSTRAINT heart_module_constraint_fk_heart
FOREIGN KEY (fk_User) REFERENCES _user (id_user);