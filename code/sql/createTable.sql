-- inserire i campi bilingua 

CREATE TABLE course (
    code VARCHAR(10) NOT NULL,
    age VARCHAR(9) NOT NULL,
    name VARCHAR(32),
    sector VARCHAR(10),
    language VARCHAR(32),
    semester INT(2),
    prerequisite VARCHAR(300),
    objective VARCHAR(300),
    exame_mode VARCHAR(32),
    teaching_mode VARCHAR(32),
    syllabus TEXT,
    note VARCHAR(300),
    knowledge VARCHAR(300),
    application VARCHAR(300),
    evaluation VARCHAR(300),    
    communication VARCHAR(300),
    lifelog_learning_skills VARCHAR(300),
primary key (code, age)
);

CREATE TABLE user (
    id INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
    name VARCHAR(32),
primary key (id)
);

CREATE TABLE course_user(
    course_code VARCHAR(10) NOT NULL,
    course_age VARCHAR(9) NOT NULL,
    user_id INT(10) UNSIGNED NOT NULL,
    primary key (course_code, course_age, id_user), 
	
	FOREIGN KEY (course_code)
    		REFERENCES course(code),
	FOREIGN KEY (course_age)
    		REFERENCES course(age),
	FOREIGN KEY (user_id)
    		REFERENCES user(id)
);

CREATE TABLE grups (
    id INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
    name VARCHAR(32),
    description VARCHAR(300),
primary key (id)
);

CREATE TABLE service (
    id INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
    name VARCHAR(32),
    description VARCHAR(300),
primary key (id)
);


CREATE TABLE studyCourse (
    id INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
    name VARCHAR(32),
    description VARCHAR(300),
    department VARCHAR(32),
    level VARCHAR(10),
    duration INT(2),
    class VARCHAR(32),
    seat VARCHAR(32),
    accessType VARCHAR(32),
    language VARCHAR(32),
primary key (id)
);

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
