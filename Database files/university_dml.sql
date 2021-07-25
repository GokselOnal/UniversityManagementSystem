-- User types
insert into type(user_type,user_type_name) values ('1','Student');
insert into type(user_type,user_type_name) values ('2','Teacher');
insert into type(user_type,user_type_name) values ('3','Admin');

-- Levels
insert into level(level_name) values('Undergraduate');
insert into level(level_name) values('Master');
insert into level(level_name) values('PhD');

-- Departments
insert into department(did,department_name) values('CS','Computer Science');
insert into department(did,department_name) values('CE','Civil Engineering');
insert into department(did,department_name) values('IE','Industrial Engineering');
insert into department(did,department_name) values('ME','Mechanical Engineering');
insert into department(did,department_name) values('EE','Electrical Engineering');

-- Teachers
insert into user(name,password,age,e_mail,address,phone,birthdate,department_id,type) values('Olcay','tp1','52','olcay@gmail.com','Istanbul','01234567898','1969-11-11','CS','2');
insert into user(name,password,age,e_mail,address,phone,birthdate,department_id,type) values('Nitel','tp2','32','nitel@gmail.com','Istanbul','22222222222','1689-10-12','CS','2');
insert into user(name,password,age,e_mail,address,phone,birthdate,department_id,type) values('Hasan','tp3','12','hasan@gmail.com','Istanbul','22222222223','2009-09-13','CS','2');
insert into user(name,password,age,e_mail,address,phone,birthdate,department_id,type) values('Hüseyin','tp4','5','huseyin@gmail.com','Istanbul','22222222224','2016-08-14','CS','2');
insert into user(name,password,age,e_mail,address,phone,birthdate,department_id,type) values('Fatih','tp5','25','fatih@gmail.com','Istanbul','22222222225','1996-07-15','CS','2');
insert into user(name,password,age,e_mail,address,phone,birthdate,department_id,type) values('Emre','tp6','35','emre@gmail.com','Istanbul','22222222226','1986-06-16','CS','2');
insert into user(name,password,age,e_mail,address,phone,birthdate,department_id,type) values('Ismail','tp7','45','ismail@gmail.com','Istanbul','22222222227','1976-05-17','CS','2');
insert into user(name,password,age,e_mail,address,phone,birthdate,department_id,type) values('Furkan','tp8','55','furkan@gmail.com','Istanbul','22222222228','1966-04-18','CS','2');
insert into user(name,password,age,e_mail,address,phone,birthdate,department_id,type) values('Reyhan','tp9','3','reyhan@gmail.com','Istanbul','22222222229','2018-03-19','CS','2');
insert into user(name,password,age,e_mail,address,phone,birthdate,department_id,type) values('Reha','tp10','23','reha@gmail.com','Istanbul','22222222210','1998-02-20','CS','2');
insert into user(name,password,age,e_mail,address,phone,birthdate,department_id,type) values('Ethem','tp11','33','ethem@gmail.com','Istanbul','22222222211','1988-01-21','CS','2');

-- Courses
insert into course(cid, teacher, title, credit, semester, year, day, start_time, duration, department_id) values ('CS101',1,'Introduction to Programming',6,'Fall','2021','Monday','08:40:00',4,'CS');
insert into course(cid, teacher, title, credit, semester, year, day, start_time, duration, prerequisite, department_id) values ('CS102',2,'Object Oriented Programming',6,'Spring','2021','Monday','08:40:00',4,'CS101','CS');
insert into course(cid, teacher, title, credit, semester, year, day, start_time, duration, prerequisite, department_id) values ('CS112',10,'Discrete Mathematics',6,'Fall','2021','Tuesday','16:40:00',4,'CS101','CS');
insert into course(cid, teacher, title, credit, semester, year, day, start_time, duration, prerequisite, department_id) values ('CS201',3,'Data Structures and Algorithms',6,'Spring','2021','Wednesday','08:40:00',4,'CS102','CS');
insert into course(cid, teacher, title, credit, semester, year, day, start_time, duration, prerequisite, department_id) values ('CS202',4,'Database Management Systems',6,'Spring','2021','Wednesday','14:40:00',3,'CS102','CS');
insert into course(cid, teacher, title, credit, semester, year, day, start_time, duration, department_id) values ('CS240',5,'Computer Architecture',6,'Spring','2021','Thursday','08:40:00',3,'CS');
insert into course(cid, teacher, title, credit, semester, year, day, start_time, duration, prerequisite, department_id) values ('CS320',3,'Software Engineering',6,'Fall','2021','Tuesday','15:40:00',3,'CS102','CS');
insert into course(cid, teacher, title, credit, semester, year, day, start_time, duration, prerequisite, department_id) values ('CS321',8,'Programming Paradigms',6,'Fall','2021','Tuesday','10:40:00',3,'CS102','CS');
insert into course(cid, teacher, title, credit, semester, year, day, start_time, duration, prerequisite, department_id) values ('CS333',6,'Analysis of Algorithms',6,'Spring','2021','Wednesday','08:40:00',4,'CS201','CS');
insert into course(cid, teacher, title, credit, semester, year, day, start_time, duration, prerequisite, department_id) values ('CS350',7,'Operating Systems',6,'Spring','2021','Monday','09:40:00',3,'CS201','CS');
insert into course(cid, teacher, title, credit, semester, year, day, start_time, duration, prerequisite, department_id) values ('CS447',10,'Computer Networks',6,'Fall','2021','Friday','11:40:00',3,'CS201','CS');
insert into course(cid, teacher, title, credit, semester, year, day, start_time, duration, prerequisite, department_id) values ('CS410',6,'Automata Theory and Formal Languages',6,'Fall','2021','Thursday','12:40:00',3,'CS201','CS');
insert into course(cid, teacher, title, credit, semester, year, day, start_time, duration, prerequisite, department_id) values ('CS451',9,'Introduction to Artificial Intelligence',6,'Spring','2021','Wednesday','14:40:00',3,'CS202','CS');
insert into course(cid, teacher, title, credit, semester, year, day, start_time, duration, prerequisite, department_id) values ('CS454',11,'Introduction to Machine Learning and Neural Networks',6,'Fall','2021','Monday','13:40:00',3,'CS101','CS');



insert into user(name,password,age,e_mail,address,phone,birthdate,level_id,department_id,type) values ('Goksel','gg',23,'can.onal@ozu.edu.tr','Samsun','05385799597','1997-03-10',1,'CS','1');
insert into user(name,password,age,e_mail,address,phone,birthdate,department_id,type) values('teacherName','tt','52','teacher@gmail.com','Istanbul','01234567898','1600-11-11','CS','2');
insert into admin (name,password) values ('admin','aa');


