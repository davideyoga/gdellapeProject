-- phpMyAdmin SQL Dump
-- version 4.5.4.1deb2ubuntu2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: May 26, 2017 at 03:32 PM
-- Server version: 5.7.18-0ubuntu0.16.04.1
-- PHP Version: 7.0.15-0ubuntu0.16.04.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `gdellapeProject`
--

-- --------------------------------------------------------

--
-- Table structure for table `book`
--

CREATE TABLE `book` (
  `idCourse` int(10) UNSIGNED NOT NULL,
  `code` varchar(32) DEFAULT NULL,
  `author` varchar(64) DEFAULT NULL,
  `title` varchar(32) DEFAULT NULL,
  `volume` varchar(32) DEFAULT NULL,
  `age` int(10) DEFAULT NULL,
  `editor` varchar(32) DEFAULT NULL,
  `link` varchar(64) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `borrowedCourse`
--

CREATE TABLE `borrowedCourse` (
  `course_id` int(16) UNSIGNED NOT NULL,
  `corse_borrowed_id` int(10) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `course`
--

CREATE TABLE `course` (
  `idCourse` int(16) UNSIGNED NOT NULL,
  `code` varchar(8) NOT NULL,
  `name` varchar(32) NOT NULL,
  `year` varchar(9) DEFAULT NULL,
  `cfu` int(5) DEFAULT NULL,
  `sector` varchar(16) DEFAULT NULL,
  `language` varchar(32) DEFAULT NULL,
  `semester` int(2) DEFAULT NULL,
  `prerequisite_ita` text,
  `prerequisite_eng` text,
  `goals_ita` text,
  `goals_eng` text,
  `exame_mode_ita` text,
  `exame_mode_eng` text,
  `teaching_mode_ita` text,
  `teaching_mode_eng` text,
  `syllabus_ita` text,
  `syllabus_eng` text,
  `note_ita` text,
  `note_eng` text,
  `knowledge_ita` text,
  `knowledge_eng` text,
  `application_ita` text,
  `application_eng` text,
  `evaluation_ita` text,
  `evaluation_eng` text,
  `communication_ita` text,
  `communication_eng` text,
  `lifelog_learning_skills_ita` text,
  `lifelog_learning_skills_eng` text,
  `external_material_ita` text,
  `external_material_eng` text
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `course_book`
--

CREATE TABLE `course_book` (
  `course_id` int(16) UNSIGNED NOT NULL,
  `book_id` int(10) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `course_material`
--

CREATE TABLE `course_material` (
  `course_id` int(16) UNSIGNED NOT NULL,
  `material_id` int(10) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `course_studyCourse`
--

CREATE TABLE `course_studyCourse` (
  `course_id` int(16) UNSIGNED NOT NULL,
  `studyCourse_id` int(10) UNSIGNED NOT NULL,
  `cfuType` varchar(8) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `course_user`
--

CREATE TABLE `course_user` (
  `course_id` int(16) UNSIGNED NOT NULL,
  `user_id` int(10) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `groups`
--

CREATE TABLE `groups` (
  `idCourse` int(10) UNSIGNED NOT NULL,
  `name` varchar(32) DEFAULT NULL,
  `description` text
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `groups_service`
--

CREATE TABLE `groups_service` (
  `service_id` int(10) UNSIGNED NOT NULL,
  `groups_id` int(10) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `log`
--

CREATE TABLE `log` (
  `idCourse` int(10) UNSIGNED NOT NULL,
  `user_id` int(10) UNSIGNED DEFAULT NULL,
  `description` text
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `material`
--

CREATE TABLE `material` (
  `idCourse` int(10) UNSIGNED NOT NULL,
  `description_ita` text,
  `description_eng` text,
  `date` datetime DEFAULT NULL,
  `size` double NOT NULL,
  `type` varchar(32) DEFAULT NULL,
  `route` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `moduleCourse`
--

CREATE TABLE `moduleCourse` (
  `course_id` int(16) UNSIGNED NOT NULL,
  `corse_module_id` int(10) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `PreparatoryCourse`
--

CREATE TABLE `preparatoryCourse` (
  `course_id` int(16) UNSIGNED NOT NULL,
  `corse_preparatory_id` int(10) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `service`
--

CREATE TABLE `service` (
  `idCourse` int(10) UNSIGNED NOT NULL,
  `name` varchar(32) DEFAULT NULL,
  `description` text
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `studyCourse`
--

CREATE TABLE `studyCourse` (
  `idCourse` int(10) UNSIGNED NOT NULL,
  `code` varchar(8) NOT NULL,
  `name` varchar(32) NOT NULL,
  `description_ita` text,
  `description_eng` text,
  `department_ita` varchar(32) DEFAULT NULL,
  `department_eng` varchar(32) DEFAULT NULL,
  `level_ita` varchar(10) DEFAULT NULL,
  `level_eng` varchar(10) DEFAULT NULL,
  `duration` int(2) DEFAULT NULL,
  `class` varchar(8) DEFAULT NULL,
  `seat` varchar(32) DEFAULT NULL,
  `accessType_ita` varchar(32) DEFAULT NULL,
  `accessType_eng` varchar(32) DEFAULT NULL,
  `language_ita` varchar(32) DEFAULT NULL,
  `language_eng` varchar(32) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `idCourse` int(10) UNSIGNED NOT NULL,
  `surname` varchar(32) DEFAULT NULL,
  `name` varchar(32) DEFAULT NULL,
  `email` varchar(64) DEFAULT NULL,
  `number` int(14) DEFAULT NULL,
  `curriculum_ita` text,
  `curriculum_eng` text,
  `receprion_hours_ita` text,
  `receprion_hours_eng` text,
  `password` varchar(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`idCourse`, `surname`, `name`, `email`, `number`, `curriculum_ita`, `curriculum_eng`, `receprion_hours_ita`, `receprion_hours_eng`, `password`) VALUES
(1, '', 'davide', 'd.micarelli7@gmail.com', 0, '', '', '', '', 'Lorenzo1');

-- --------------------------------------------------------

--
-- Table structure for table `user_groups`
--

CREATE TABLE `user_groups` (
  `user_id` int(10) UNSIGNED NOT NULL,
  `groups_id` int(10) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `book`
--
ALTER TABLE `book`
  ADD PRIMARY KEY (`idCourse`);

--
-- Indexes for table `borrowedCourse`
--
ALTER TABLE `borrowedCourse`
  ADD PRIMARY KEY (`course_id`,`corse_borrowed_id`),
  ADD KEY `corse_borrowed_id` (`corse_borrowed_id`);

--
-- Indexes for table `course`
--
ALTER TABLE `course`
  ADD PRIMARY KEY (`idCourse`);

--
-- Indexes for table `course_book`
--
ALTER TABLE `course_book`
  ADD KEY `course_id` (`course_id`),
  ADD KEY `book_id` (`book_id`);

--
-- Indexes for table `course_material`
--
ALTER TABLE `course_material`
  ADD KEY `course_id` (`course_id`),
  ADD KEY `material_id` (`material_id`);

--
-- Indexes for table `course_studyCourse`
--
ALTER TABLE `course_studyCourse`
  ADD KEY `course_id` (`course_id`),
  ADD KEY `studyCourse_id` (`studyCourse_id`);

--
-- Indexes for table `course_user`
--
ALTER TABLE `course_user`
  ADD PRIMARY KEY (`course_id`,`user_id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `groups`
--
ALTER TABLE `groups`
  ADD PRIMARY KEY (`idCourse`);

--
-- Indexes for table `groups_service`
--
ALTER TABLE `groups_service`
  ADD KEY `service_id` (`service_id`),
  ADD KEY `groups_id` (`groups_id`);

--
-- Indexes for table `log`
--
ALTER TABLE `log`
  ADD PRIMARY KEY (`idCourse`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `material`
--
ALTER TABLE `material`
  ADD PRIMARY KEY (`idCourse`),
  ADD UNIQUE KEY `idCourse` (`idCourse`);

--
-- Indexes for table `moduleCourse`
--
ALTER TABLE `moduleCourse`
  ADD PRIMARY KEY (`course_id`,`corse_module_id`),
  ADD KEY `corse_module_id` (`corse_module_id`);

--
-- Indexes for table `PreparatoryCourse`
--
ALTER TABLE `preparatoryCourse`
  ADD PRIMARY KEY (`course_id`,`corse_preparatory_id`),
  ADD KEY `corse_preparatory_id` (`corse_preparatory_id`);

--
-- Indexes for table `service`
--
ALTER TABLE `service`
  ADD PRIMARY KEY (`idCourse`);

--
-- Indexes for table `studyCourse`
--
ALTER TABLE `studyCourse`
  ADD PRIMARY KEY (`idCourse`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`idCourse`);

--
-- Indexes for table `user_groups`
--
ALTER TABLE `user_groups`
  ADD KEY `user_id` (`user_id`),
  ADD KEY `groups_id` (`groups_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `book`
--
ALTER TABLE `book`
  MODIFY `idCourse` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `course`
--
ALTER TABLE `course`
  MODIFY `idCourse` int(16) UNSIGNED NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `groups`
--
ALTER TABLE `groups`
  MODIFY `idCourse` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `material`
--
ALTER TABLE `material`
  MODIFY `idCourse` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `service`
--
ALTER TABLE `service`
  MODIFY `idCourse` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `studyCourse`
--
ALTER TABLE `studyCourse`
  MODIFY `idCourse` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `idCourse` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `borrowedCourse`
--
ALTER TABLE `borrowedCourse`
  ADD CONSTRAINT `borrowedCourse_ibfk_1` FOREIGN KEY (`course_id`) REFERENCES `course` (`idCourse`),
  ADD CONSTRAINT `borrowedCourse_ibfk_2` FOREIGN KEY (`corse_borrowed_id`) REFERENCES `course` (`idCourse`);

--
-- Constraints for table `course_book`
--
ALTER TABLE `course_book`
  ADD CONSTRAINT `course_book_ibfk_1` FOREIGN KEY (`course_id`) REFERENCES `course` (`idCourse`),
  ADD CONSTRAINT `course_book_ibfk_2` FOREIGN KEY (`book_id`) REFERENCES `book` (`idCourse`);

--
-- Constraints for table `course_material`
--
ALTER TABLE `course_material`
  ADD CONSTRAINT `course_material_ibfk_1` FOREIGN KEY (`course_id`) REFERENCES `course` (`idCourse`),
  ADD CONSTRAINT `course_material_ibfk_2` FOREIGN KEY (`material_id`) REFERENCES `material` (`idCourse`);

--
-- Constraints for table `course_studyCourse`
--
ALTER TABLE `course_studyCourse`
  ADD CONSTRAINT `course_studyCourse_ibfk_1` FOREIGN KEY (`course_id`) REFERENCES `course` (`idCourse`),
  ADD CONSTRAINT `course_studyCourse_ibfk_2` FOREIGN KEY (`studyCourse_id`) REFERENCES `studyCourse` (`idCourse`);

--
-- Constraints for table `course_user`
--
ALTER TABLE `course_user`
  ADD CONSTRAINT `course_user_ibfk_1` FOREIGN KEY (`course_id`) REFERENCES `course` (`idCourse`),
  ADD CONSTRAINT `course_user_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`idCourse`);

--
-- Constraints for table `groups_service`
--
ALTER TABLE `groups_service`
  ADD CONSTRAINT `groups_service_ibfk_1` FOREIGN KEY (`service_id`) REFERENCES `service` (`idCourse`),
  ADD CONSTRAINT `groups_service_ibfk_2` FOREIGN KEY (`groups_id`) REFERENCES `groups` (`idCourse`);

--
-- Constraints for table `log`
--
ALTER TABLE `log`
  ADD CONSTRAINT `log_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`idCourse`);

--
-- Constraints for table `moduleCourse`
--
ALTER TABLE `moduleCourse`
  ADD CONSTRAINT `moduleCourse_ibfk_1` FOREIGN KEY (`course_id`) REFERENCES `course` (`idCourse`),
  ADD CONSTRAINT `moduleCourse_ibfk_2` FOREIGN KEY (`corse_module_id`) REFERENCES `course` (`idCourse`);

--
-- Constraints for table `PreparatoryCourse`
--
ALTER TABLE `preparatoryCourse`
  ADD CONSTRAINT `preparatoryCourse_ibfk_1` FOREIGN KEY (`course_id`) REFERENCES `course` (`idCourse`),
  ADD CONSTRAINT `preparatoryCourse_ibfk_2` FOREIGN KEY (`corse_preparatory_id`) REFERENCES `course` (`idCourse`);

--
-- Constraints for table `user_groups`
--
ALTER TABLE `user_groups`
  ADD CONSTRAINT `user_groups_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`idCourse`),
  ADD CONSTRAINT `user_groups_ibfk_2` FOREIGN KEY (`groups_id`) REFERENCES `groups` (`idCourse`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
