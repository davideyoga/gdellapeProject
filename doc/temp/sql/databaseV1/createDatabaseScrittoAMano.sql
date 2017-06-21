-- inserire i campi bilingua 

CREATE TABLE course (
    idCourse INT(16) UNSIGNED AUTO_INCREMENT NOT NULL,
    code VARCHAR(8) NOT NULL,
    name VARCHAR(32) NOT NULL,
    year VARCHAR(9),
    cfu int(5),
    sector VARCHAR(16),
    language VARCHAR(32),
    semester INT(2),
    prerequisite_ita TEXT,
    prerequisite_eng TEXT,
    goals_ita TEXT,
    goals_eng TEXT,
    exame_mode_ita TEXT,
    exame_mode_eng TEXT,
    teaching_mode_ita TEXT,
    teaching_mode_eng TEXT,
    syllabus_ita TEXT,
    syllabus_eng TEXT,
    note_ita TEXT,
    note_eng TEXT, 		/*NOTE DEL CORSO*/
    knowledge_ita TEXT,
    knowledge_eng TEXT,
    application_ita TEXT,
    application_eng TEXT,
    evaluation_ita TEXT,
    evaluation_eng TEXT,    
    communication_ita TEXT,
    communication_eng TEXT,
    lifelog_learning_skills_ita TEXT,
    lifelog_learning_skills_eng TEXT,
    external_material_ita TEXT,
    external_material_eng TEXT,
primary key (idCourse)
);

CREATE TABLE borrowedCourse ( /* corso mutuato*/
    course_id INT(16) UNSIGNED NOT NULL,
    corse_borrowed_id INT(10) UNSIGNED NOT NULL,
    primary key (course_id, corse_borrowed_id), 
	
	FOREIGN KEY (course_id)
    		REFERENCES course(idCourse),
	FOREIGN KEY (corse_borrowed_id)
    		REFERENCES course(idCourse)
)

CREATE TABLE preparatoryCourse (
    course_id INT(16) UNSIGNED NOT NULL,
    corse_preparatory_id INT(10) UNSIGNED NOT NULL,
    primary key (course_id, corse_preparatory_id), 
	
	FOREIGN KEY (course_id)
    		REFERENCES course(idCourse),
	FOREIGN KEY (corse_preparatory_id)
    		REFERENCES course(idCourse)
)

CREATE TABLE moduleCourse (
    course_id INT(16) UNSIGNED NOT NULL,
    corse_module_id INT(10) UNSIGNED NOT NULL,
primary key (course_id, corse_module_id), 
	
	FOREIGN KEY (course_id)
    		REFERENCES course(idCourse),
	FOREIGN KEY (corse_module_id)
    		REFERENCES course(idCourse)

)

CREATE TABLE user (
    idCourse INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
    surname VARCHAR(32),
    name VARCHAR(32),
    email VARCHAR(64),
    number INT(14),
    curriculum_ita TEXT,
    curriculum_eng TEXT,
    receprion_hours_ita TEXT,
    receprion_hours_eng TEXT,
primary key (idCourse)
);

CREATE TABLE course_user(
    course_id INT(16) UNSIGNED NOT NULL,
    user_id INT(10) UNSIGNED NOT NULL,
    primary key (course_id, user_id), 
	
	FOREIGN KEY (course_id)
    		REFERENCES course(idCourse),
	FOREIGN KEY (user_id)
    		REFERENCES user(idCourse)
);

CREATE TABLE groups (
    idCourse INT(10) UNSIGNED AUTO_INCREMENT NOT NULL,
    name VARCHAR(32),
    description TEXT,
primary key (idCourse)
);

CREATE TABLE user_groups (
    user_id INT(10) UNSIGNED NOT NULL,
    groups_id INT(10) UNSIGNED NOT NULL,

    FOREIGN KEY (user_id)
    	REFERENCES user(idCourse),
	FOREIGN KEY (groups_id)
    	REFERENCES groups(idCourse)
)

CREATE TABLE service (
    idCourse INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
    name VARCHAR(32),
    description TEXT,
primary key (idCourse)
);

CREATE TABLE groups_service (
    service_id INT(10) UNSIGNED NOT NULL,
    groups_id INT(10) UNSIGNED NOT NULL,

    FOREIGN KEY (service_id)
    	REFERENCES service(idCourse),
	FOREIGN KEY (groups_id)
    	REFERENCES groups(idCourse)
)

CREATE TABLE studyCourse (
    idCourse INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
    code VARCHAR(8) NOT NULL,
    name VARCHAR(32) NOT NULL,
    description_ita TEXT,
    description_eng TEXT,
    department_ita VARCHAR(32),
    department_eng VARCHAR(32),
    level_ita VARCHAR(10),
    level_eng VARCHAR(10),
    duration INT(2),
    class VARCHAR(8),
    seat VARCHAR(32),
    accessType_ita VARCHAR(32),
    accessType_eng VARCHAR(32),
    language_ita VARCHAR(32),
    language_eng VARCHAR(32),
primary key (idCourse)
);

CREATE TABLE course_studyCourse (
    course_id INT(16) UNSIGNED NOT NULL,
    studyCourse_id INT(10) UNSIGNED NOT NULL,
    cfuType VARCHAR(8),

    FOREIGN KEY (course_id)
    	REFERENCES course(idCourse),
	FOREIGN KEY (studyCourse_id)
    	REFERENCES studyCourse(idCourse)
)

CREATE TABLE book(
    idCourse INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
    code VARCHAR(32),
    author VARCHAR(64), 
    title VARCHAR(32),
    volume VARCHAR(32),
    age INT(10),
    editor VARCHAR(32),
    link VARCHAR(64),
primary key (idCourse)
);

CREATE TABLE course_book (
    course_id INT(16) UNSIGNED NOT NULL,
    book_id INT(10) UNSIGNED NOT NULL,

    FOREIGN KEY (course_id)
    	REFERENCES course(idCourse),
	FOREIGN KEY (book_id)
    	REFERENCES book(idCourse)
)

CREATE TABLE log(
    idCourse INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
    description VARCHAR(256),
primary key (idCourse)
);

CREATE TABLE user_log (
    user_id INT(16) UNSIGNED NOT NULL,
    log_id INT(10) UNSIGNED NOT NULL,

    FOREIGN KEY (user_id)
    	REFERENCES user(idCourse),
	FOREIGN KEY (log_id)
    	REFERENCES log(idCourse)
)

CREATE TABLE material (
    idCourse INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
    description_ita TEXT,
    description_eng TEXT,
    date DATETIME,
    size real NOT NULL,
    type VARCHAR(32),
primary key (idCourse)
)

CREATE TABLE course_material (
    course_id INT(16) UNSIGNED NOT NULL,
    material_id INT(10) UNSIGNED NOT NULL,

    FOREIGN KEY (course_id)
    	REFERENCES course(idCourse),
	FOREIGN KEY (material_id)
    	REFERENCES material(idCourse)
)



