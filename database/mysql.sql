Create Database myfirstapp;
use myfirstapp;

CREATE  TABLE users (
	id int(11) not null AUTO_INCREMENT,
	fullname varchar(100) ,
	specialty varchar(100) ,
	age int(5) ,
	expirence float ,
	email varchar(100) ,
	contact varchar(15), 
	username VARCHAR(45) NOT NULL ,
	password VARCHAR(45) NOT NULL ,
	role TINYINT NOT NULL DEFAULT 1,
	enabled TINYINT NOT NULL DEFAULT 1 ,
	PRIMARY KEY (id));

CREATE TABLE user_roles (
id int(11) not null AUTO_INCREMENT PRIMARY KEY,
code varchar(30),
name varchar(40),
description varchar(500),
isInternal bit(1), 
enabled TINYINT NOT NULL DEFAULT 1 );

INSERT INTO users(fullname, specialty, age,expirence, email, contact, username,password,role,enabled)
VALUES ('Mahdi Hassahan',null,25,4,'mahdi@gmail.com','9999999999','mahdi','123456',1, true);
INSERT INTO users(fullname, specialty, age,expirence, email, contact, username,password,role,enabled)
VALUES ('saurabh sharma','General Medicine',29,5,'saurabh.find@gmail.com','9602273529','saurabh','123456',2, true);
INSERT INTO users(fullname, specialty, age,expirence, email, contact, username,password,role,enabled)
VALUES ('Abdul',null,29,null,'abdul@gmail.com','6756789043','abdul','123456',3, true);


INSERT INTO user_roles (code,name,description, isInternal,enabled)
VALUES ('ROLE_ADMIN','Administrator','Administrator',1,1);
INSERT INTO user_roles (code,name,description, isInternal,enabled)
VALUES ('ROLE_DOCTOR','Doctor','Doctor',0,1);
INSERT INTO user_roles (code,name,description, isInternal,enabled)
VALUES ('ROLE_USER','user','User',0,1);