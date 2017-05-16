-- inserire i campi bilingua 

CREATE TABLE course (
    id INT(16) UNSIGNED AUTO_INCREMENT NOT NULL, 
    code VARCHAR(10) NOT NULL,
    year VARCHAR(9),
    name VARCHAR(32) NOT NULL,
    sector VARCHAR(10),
    language VARCHAR(32),
    semester INT(2),
    prerequisite TEXT,
    goals TEXT,
    exame_mode TEXT,
    teaching_mode TEXT,
    syllabus TEXT,
    note TEXT, 		/*NOTE DEL CORSO*/
    knowledge TEXT,
    application TEXT,
    evaluation TEXT,    
    communication TEXT,
    lifelog_learning_skills TEXT,
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

CREATE TABLE groups (
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
