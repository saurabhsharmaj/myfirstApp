Create Database myfirstapp;
use myfirstapp;
SET FOREIGN_KEY_CHECKS=0;
-- Created by Saurabh Sharma
-- Last modification date: 2015-12-08 05:12:46.983

-- tables
-- Table appointment
CREATE TABLE appointment (
    id int  NOT NULL AUTO_INCREMENT,
    appointmentDate timestamp  NOT NULL,
    status int  NOT NULL,
    patient_id int  NOT NULL,
    doctor_id int  NOT NULL,
    CONSTRAINT appointment_patient_pk PRIMARY KEY (id)
);

-- Table user_roles
CREATE TABLE user_roles (
    id int  NOT NULL AUTO_INCREMENT,
    code varchar(50)  NOT NULL,
    name varchar(40),
	description varchar(500),
	isInternal bit(1), 
	enabled TINYINT NOT NULL DEFAULT 1,
    CONSTRAINT user_roles_pk PRIMARY KEY (id)
);

-- Table users
CREATE TABLE users (
    id int  NOT NULL AUTO_INCREMENT,
    fullname varchar(50)  NOT NULL,
    role int  NOT NULL,
    specialty varchar(100) ,
	age int(5) ,
	expirence float ,
	email varchar(100) ,
	contact varchar(15), 
	username VARCHAR(45) NOT NULL ,
	password VARCHAR(45) NOT NULL ,	
	profilePicUrl varchar(20) DEFAULT 'profilePic.jpg',
	enabled TINYINT DEFAULT 1,
	summary TEXT,
    CONSTRAINT users_pk PRIMARY KEY (id)
);





-- foreign keys
-- Reference:  appointment_users (table: appointment)


ALTER TABLE appointment ADD CONSTRAINT appointment_patient FOREIGN KEY appointment_users (patient_id)
    REFERENCES users (id);

ALTER TABLE appointment ADD CONSTRAINT appointment_doctor FOREIGN KEY appointment_users (doctor_id)
    REFERENCES users (id);
-- Reference:  users_user_roles (table: users)


ALTER TABLE users ADD CONSTRAINT users_user_roles FOREIGN KEY  users(role)
    REFERENCES user_roles (id);

-- Intest for User_Roles
INSERT INTO `myfirstapp`.`user_roles` (`id`, `code`, `name`, `description`, `isInternal`, `enabled`) VALUES ('1', 'ROLE_ADMIN', 'Administrator', 'Administrator', 1, '1');
INSERT INTO `myfirstapp`.`user_roles` (`id`, `code`, `name`, `description`, `enabled`) VALUES ('2', 'ROLE_DOCTOR', 'Doctor', 'Doctor', '1');
INSERT INTO `myfirstapp`.`user_roles` (`id`, `code`, `name`, `description`, `enabled`) VALUES ('3', 'ROLE_USER', 'Patient', 'Patient', '1');


-- Insert for Users
INSERT INTO `myfirstapp`.`users` (`id`, `fullname`, `role`, `email`, `contact`, `username`, `password`, `profilePicUrl`, `enabled`) VALUES ('1', 'administrator', '1', 'admin@gmail.com', '1234567890', 'admin', 'demo', 'profilePic.jpg', '1');
INSERT INTO `myfirstapp`.`users` (`id`, `fullname`, `role`, `specialty`, `age`, `expirence`, `email`, `contact`, `username`, `password`, `profilePicUrl`, `enabled`) VALUES ('2', 'doctor', '2', 'ortho', '25', '5', 'doctor@gmail.com', '1234567890', 'doctor', 'demo', 'profilePic.jpg', '1');
INSERT INTO `myfirstapp`.`users` (`id`, `fullname`, `role`, `age`, `email`, `contact`, `username`, `password`, `profilePicUrl`, `enabled`) VALUES ('3', 'patient', '3', '35', 'patient', '1234567890', 'patient', 'demo', 'profilePic.jpg', '1');

-- End of file.
ALTER TABLE `myfirstapp`.`users` ADD COLUMN `summary` TEXT(4000) NULL ;
