CREATE TABLE course_module (
    course_module_id BINARY(16) PRIMARY KEY,
    module_name VARCHAR(40) NOT NULL,
    module_order INT NOT NULL,
    course_fk BINARY(16) NOT NULL
)