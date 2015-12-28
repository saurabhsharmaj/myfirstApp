drop database getdoc;
create database getdoc;
use getdoc;

--
-- Database: `getdoc`
--

-- --------------------------------------------------------

--
-- Table structure for table `appointment_schedule`
--

CREATE TABLE IF NOT EXISTS `appointment_schedule` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `working_days` varchar(50) DEFAULT NULL,
  `start_time` dateTime DEFAULT NULL,
  `end_time` dateTime DEFAULT NULL,
  `slot_size` int(11) DEFAULT NULL,  
  PRIMARY KEY (`id`)
) AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `bookings`
--

CREATE TABLE IF NOT EXISTS `bookings` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `doctor_id` int(11) NOT NULL,  
  `patient_id` int(11) NOT NULL,
  `diseases_description` text NOT NULL,
  `datetime_start` datetime NOT NULL,
  `datetime_end` datetime NOT NULL,
  `status_id` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
) AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `booking_status`
--

CREATE TABLE IF NOT EXISTS `booking_status` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(25) NOT NULL,
  `code` int NOT NULL,
  PRIMARY KEY (`id`)
)  AUTO_INCREMENT=6 ;

--
-- Dumping data for table `booking_status`
--

INSERT INTO `booking_status` (`id`, `name`, `code`) VALUES
(1, 'Approved & Booked',1),
(2, 'Cancelled',2);


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

-- Intest for User_Roles
INSERT INTO `user_roles` (`id`, `code`, `name`, `description`, `isInternal`, `enabled`) VALUES ('1', 'ROLE_ADMIN', 'Administrator', 'Administrator', 1, '1');
INSERT INTO `user_roles` (`id`, `code`, `name`, `description`, `enabled`) VALUES ('2', 'ROLE_DOCTOR', 'Doctor', 'Doctor', '1');
INSERT INTO `user_roles` (`id`, `code`, `name`, `description`, `enabled`) VALUES ('3', 'ROLE_PATIENT', 'Patient', 'Patient', '1');


-- --------------------------------------------------------

--
-- Table structure for table `doctors`
--

CREATE TABLE IF NOT EXISTS `users` (
  	`id` int(11) NOT NULL AUTO_INCREMENT,
  	`specialization_id` int(11),
  	`fullname` varchar(50),		
	`clinic_name` varchar(50),
	`address` varchar(1000),
	`qualification` varchar(50),
	`rating` int(11),
	`age` int(5) ,
	`expirence` float ,
	`email` varchar(100) ,
	`contact` varchar(15), 
	`username` VARCHAR(45) NOT NULL ,
	`password` VARCHAR(45) NOT NULL ,	
	`profilePicUrl` varchar(50) DEFAULT 'patientProfilePic.jpg',
	`enabled` TINYINT DEFAULT 1,
	`summary` TEXT,
  PRIMARY KEY (`id`)
)  AUTO_INCREMENT=1 ;

-- ALTER TABLE users ADD CONSTRAINT users_user_roles FOREIGN KEY  users(role) REFERENCES user_roles (id);
  
-- ALTER TABLE users ADD CONSTRAINT users_appointment_schedule FOREIGN KEY  users(appointmentSchedule)    REFERENCES appointment_schedule (id);
    
-- ALTER TABLE bookings ADD CONSTRAINT bookings_patient FOREIGN KEY bookings (patient_id) REFERENCES users (id);

-- ALTER TABLE bookings ADD CONSTRAINT bookings_doctor FOREIGN KEY bookings (doctor_id) REFERENCES users (id);

-- ALTER TABLE bookings ADD CONSTRAINT bookings_appointment_schedule FOREIGN KEY bookings(appointment_schedule_id) REFERENCES appointment_schedule (id);
    
-- ALTER TABLE appointment_schedule ADD CONSTRAINT appointment_schedule_doctor FOREIGN KEY appointment_schedule (doctors_id) REFERENCES users (id);

-- ALTER TABLE bookings ADD CONSTRAINT bookings_booking_status FOREIGN KEY bookings (status_id) REFERENCES booking_status (id);

    
INSERT INTO `users` (`fullname`, `email`, `username`, `password`, `profilePicUrl`, `enabled`, `summary`) VALUES ('administrator', 'admin@gmail.com', 'admin', 'demo', 'adminProfilePic.jpg', '1', 'Manager the overall system');
INSERT INTO `users` (`fullname`, `email`, `username`, `password`, `profilePicUrl`, `enabled`, `summary`) VALUES ('doctor', 'doctor@gmail.com', 'doctor', 'demo', 'doctorProfilePic.jpg', '1', 'Waiting for patients');
INSERT INTO `users` (`fullname`, `email`, `username`, `password`, `profilePicUrl`, `enabled`, `summary`) VALUES ('patient', 'patient@gmail.com', 'patient', 'demo', 'patientProfilePic.jpg', '1', 'Want to Get Appointment.');

-- --------------------------------------------------------
CREATE TABLE IF NOT EXISTS `user_role_mapping` (
 `user_id` int(11) NOT NULL,
 `role` int(11) NOT NULL,  
  PRIMARY KEY (user_id,role)
) AUTO_INCREMENT=1 ;


INSERT INTO `user_role_mapping` (`user_id`, `role`) VALUES (1,1);
INSERT INTO `user_role_mapping` (`user_id`, `role`) VALUES (2,2);
INSERT INTO `user_role_mapping` (`user_id`, `role`) VALUES (3,3);

--
-- Table structure for table `specialization`
--

CREATE TABLE IF NOT EXISTS `specialization` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `Description` varchar(1000) NOT NULL,
  PRIMARY KEY (`id`)
) AUTO_INCREMENT=1 ;


INSERT INTO `specialization` ( `name`, `Description`) VALUES ('Allergy & Immunology','Allergic and immunologic diseases and their respiratory complications (such as pollen, chemical and food allergies, asthma and AIDS).');
INSERT INTO `specialization` ( `name`, `Description`) VALUES ('Anesthesia','Anesthesia or relief of pain during surgery and childbirth, and control of paid due to various causes.');
INSERT INTO `specialization` ( `name`, `Description`) VALUES ('Cardiovascular Disease','Diseases of the heart and blood vessels.');
INSERT INTO `specialization` ( `name`, `Description`) VALUES ('Cardiology','');
INSERT INTO `specialization` ( `name`, `Description`) VALUES ('Dermatology','Diseases of the skin.');
INSERT INTO `specialization` ( `name`, `Description`) VALUES ('Emergency Medicine','Diseases that are acute medical or surgical conditions or injuries that require urgent or immediate care (usually in a hospital emergency room).');
INSERT INTO `specialization` ( `name`, `Description`) VALUES ('Endocrinology and Metabolism','Diseases of the internal glands of the body, including diabetes mellitus.');
INSERT INTO `specialization` ( `name`, `Description`) VALUES ('Family Practice','All diseases and related total health care of an individual and the family.');
INSERT INTO `specialization` ( `name`, `Description`) VALUES ('Gastroenterology','Diseases of the digestive tract, including the stomach, bowel, liver and pancreas.');
INSERT INTO `specialization` ( `name`, `Description`) VALUES ('General Practice','All diseases and related total health care of an individual and the family.');
INSERT INTO `specialization` ( `name`, `Description`) VALUES ('Geriatric Medicine','Diseases of the elderly.');
INSERT INTO `specialization` ( `name`, `Description`) VALUES ('Gynecology','See "Obstetrics and Gynecology".');
INSERT INTO `specialization` ( `name`, `Description`) VALUES ('Gynecologic Oncology','Cancer diseases of the female reproductive system.');
INSERT INTO `specialization` ( `name`, `Description`) VALUES ('Hematology','Disorders of the blood and blood-forming organs (including cancerous disorders of the blood) such as anemia, leukemia and lymphoma (see Oncology, Medical).');
INSERT INTO `specialization` ( `name`, `Description`) VALUES ('Infectious Diseases','Infections of all types.');
INSERT INTO `specialization` ( `name`, `Description`) VALUES ('Internal Medicine','All diseases and total health care of adults, usually 18 years of age and older.');
INSERT INTO `specialization` ( `name`, `Description`) VALUES ('Neonatology','Disease of the newborn child.');
INSERT INTO `specialization` ( `name`, `Description`) VALUES ('Nephrology','Diseases of the kidney, including dialysis.');
INSERT INTO `specialization` ( `name`, `Description`) VALUES ('Neurology','Diseases of the brain, spinal cord, nervous system and related structures.');
INSERT INTO `specialization` ( `name`, `Description`) VALUES ('Neurological Surgery','Diseases of the brain, spinal cord, nervous system and related structures requiring surgery.');
INSERT INTO `specialization` ( `name`, `Description`) VALUES ('Obstetrics and Gynecology','Normal and abnormal pregnancy, diseases of the female reproductive system and fertility disorders.');
INSERT INTO `specialization` ( `name`, `Description`) VALUES ('Oncology, Medical','Cancer and disorders of the blood and blood-forming organs (see Hematology).');
INSERT INTO `specialization` ( `name`, `Description`) VALUES ('Ophthalmology','Diseases of the eye.');
INSERT INTO `specialization` ( `name`, `Description`) VALUES ('Orthopedic Surgery','Diseases of the bones, joints, muscles and tendons.');
INSERT INTO `specialization` ( `name`, `Description`) VALUES ('Otorhinolaryngology','Diseases of the ears, nose, sinuses, throat and upper airway passages.');

INSERT INTO `specialization` ( `name`, `Description`) VALUES ('(Ear, Nose & Throat)','');
INSERT INTO `specialization` ( `name`, `Description`) VALUES ('Pathology','Tissues and specimens removed by biopsy and surgery to diagnose normal from diseased tissues and specimens; supervises and interprets laboratory tests on blood, urine and other body fluids.');
INSERT INTO `specialization` ( `name`, `Description`) VALUES ('Pediatrics','All diseases and total health care of newborns, infants, children and adolescents.');
INSERT INTO `specialization` ( `name`, `Description`) VALUES ('Physical Medicine and Rehabilitation','Diseases with major and minor disabilities requiring restoration of functional ability such as assistance, retraining and recondition of muscles, tendons and extremities for ambulation and other activities of daily living.');
INSERT INTO `specialization` ( `name`, `Description`) VALUES ('Plastic Surgery','Diseases and conditions requiring surgical reconstruction for deformity or loss of a body part, or for cosmetic purposes to improve appearance or function.');
INSERT INTO `specialization` ( `name`, `Description`) VALUES ('Podiatric Medicine','Disease of the foot and ankle as they affect the conditions of the feet.');

INSERT INTO `specialization` ( `name`, `Description`) VALUES ('(Podiatry)','');
INSERT INTO `specialization` ( `name`, `Description`) VALUES ('Preventative Medicine','Health care and other measures to avoid delay or prevent disease or illness from occurring.');
INSERT INTO `specialization` ( `name`, `Description`) VALUES ('Psychiatry','Diseases affecting mental health including diseases of the brain, nervous system and substance abuse of drugs or chemicals.');
INSERT INTO `specialization` ( `name`, `Description`) VALUES ('Pulmonary Disease','Diseases of the lung.');
INSERT INTO `specialization` ( `name`, `Description`) VALUES ('Radiology, Diagnostic','X-ray, ultrasound and other imaging techniques such as Computerized Tomography (CT) and Magnetic Resonance Imaging (MRI).');
INSERT INTO `specialization` ( `name`, `Description`) VALUES ('Radiology, Nuclear','Diseases requiring use of radioactive isotopes or as an aid in diagnosis and/or therapy.');
INSERT INTO `specialization` ( `name`, `Description`) VALUES ('Radiation Oncology','Cancer and other diseases with x-ray therapy, radioactive isotopes and linear accelerator particle radiation.');
INSERT INTO `specialization` ( `name`, `Description`) VALUES ('Rheumatology','Diseases of the joints including arthritis and autoimmune diseases.');
INSERT INTO `specialization` ( `name`, `Description`) VALUES ('Sports Medicine','Diseases and injuries acquired in sports.');
INSERT INTO `specialization` ( `name`, `Description`) VALUES ('Surgery, General','Disease that require surgical operation for diagnosis or treatment.');
INSERT INTO `specialization` ( `name`, `Description`) VALUES ('Surgery, Hand','Diseases and injuries of the nerves, tendons, muscles, bones or skin of the hand requiring surgery.');
INSERT INTO `specialization` ( `name`, `Description`) VALUES ('Surgery, Thoracic','Diseases of the chest, including lungs, heart, blood vessels and chest wall, that require surgical operation for diagnosis and/or treatment.');
INSERT INTO `specialization` ( `name`, `Description`) VALUES ('Surgery, Vascular','Diseases of the blood vessels that require surgical operation for diagnosis or treatment.');
INSERT INTO `specialization` ( `name`, `Description`) VALUES ('Surgery, Colon and Rectal','Diseases of the large intestine (bowel), rectum and anus that require surgical operation for diagnosis or treatment.');
INSERT INTO `specialization` ( `name`, `Description`) VALUES ('Surgery, Urology','Diseases of the kidneys, bladder and male reproductive tract that require surgical operation.');


--
INSERT INTO `getdoc`.`appointment_schedule` (`id`, `working_days`, `start_time`, `end_time`, `slot_size`) VALUES ('2', '5', '2015-12-26 10:00:00', '2015-12-26 13:00:00', '30');

ALTER TABLE users ADD CONSTRAINT `fk_users_specialization`
    FOREIGN KEY (`specialization_id`) REFERENCES `specialization` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;
    
ALTER TABLE appointment_schedule ADD CONSTRAINT `fk_appointment_schedule_users`
    FOREIGN KEY (`id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE bookings ADD CONSTRAINT `fk_bookings_users_patient`
    FOREIGN KEY (`patient_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION; 

ALTER TABLE bookings ADD CONSTRAINT `fk_bookings_users_doctor`
    FOREIGN KEY (`doctor_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE user_role_mapping ADD CONSTRAINT `fk_user_role_mapping_users`
    FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;
 
ALTER TABLE user_role_mapping ADD CONSTRAINT `fk_user_role_mapping_user_roles`
    FOREIGN KEY (`role`) REFERENCES `user_roles` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION ;
    
ALTER TABLE bookings ADD CONSTRAINT `fk_bookings_ booking_status`
    FOREIGN KEY (`status_id`) REFERENCES `booking_status` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;