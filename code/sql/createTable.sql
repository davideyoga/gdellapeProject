-- inserire i campi bilingua 

CREATE TABLE course (
    id INT(16) UNSIGNED AUTO_INCREMENT NOT NULL, 
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
primary key (id)
);

CREATE TABLE user (
    id INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
    surname VARCHAR(32),
    name VARCHAR(32),
    email VARCHAR(64),
primary key (id)
);

CREATE TABLE course_user(
    course_id INT(16) UNSIGNED NOT NULL,
    user_id INT(10) UNSIGNED NOT NULL,
    primary key (course_id, user_id), 
	
	FOREIGN KEY (course_id)
    		REFERENCES course(id),
	FOREIGN KEY (user_id)
    		REFERENCES user(id)
);

CREATE TABLE group (
    id INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
    name VARCHAR(32),
    description TEXT,
primary key (id)
);

CREATE TABLE user_group (
    user_id INT(10) UNSIGNED NOT NULL,
    group_id INT(10) UNSIGNED NOT NULL,

    FOREIGN KEY (user_id)
    	REFERENCES user(id),
	FOREIGN KEY (group_id)
    	REFERENCES group(id)     
)

CREATE TABLE service (
    id INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
    name VARCHAR(32),
    description TEXT,
primary key (id)
);

CREATE TABLE group_service (
    service_id INT(10) UNSIGNED NOT NULL,
    group_id INT(10) UNSIGNED NOT NULL,

    FOREIGN KEY (service_id)
    	REFERENCES service(id),
	FOREIGN KEY (group_id)
    	REFERENCES group(id) 
)

CREATE TABLE studyCourse (
    id INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
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
primary key (id)
);

CREATE TABLE course_studyCourse (
    course_id 
)

CREATE TABLE book(
    id INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
    code VARCHAR(32),
    author VARCHAR(64), 
    title VARCHAR(32),
    volume VARCHAR(32),
    age INT(10),
    editor VARCHAR(32),
    link VARCHAR(64),
primary key (id)
);


CREATE TABLE log(
    id INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
    description VARCHAR(256),
primary key (id)
);
