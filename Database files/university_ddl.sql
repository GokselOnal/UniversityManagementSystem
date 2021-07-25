drop table if exists type;
drop table if exists level;
drop table if exists department;
drop table if exists admin;
drop table if exists user;
drop table if exists course;
-- drop table if exists course_grouped;
drop table if exists schedule;
drop table if exists takes;
drop table if exists notes;


create table type(
	user_type enum('1','2','3'),  -- 1-> Student, 2->Teacher, 3-> Admin
    user_type_name varchar(20),
	primary key(user_type)
);

create table level(
	lid int auto_increment,
    level_name varchar(15) not null,
    UNIQUE(level_name),
    primary key(lid)
);

create table department(
	did varchar(2),
    department_name varchar(40) not null,
    UNIQUE(department_name),
    primary key(did)
);

create table admin(
	aid int auto_increment,
    name varchar(30) not null,
    password varchar(30) not null,
    primary key(aid)
);

create table user(
	uid int auto_increment,
	name varchar(30) not null,
    password varchar(30) not null,
    age tinyint not null,
    e_mail varchar(30) not null,
    address varchar(30) not null,
    phone char(11) not null,
    birthdate date not null,
    level_id int, 
    department_id varchar(2),
    credit int default 30,
    type enum('1','2','3') not null,
    primary key(uid),
    foreign key(level_id) references level(lid),
    foreign key(department_id) references department(did) on delete set null on update cascade,
    foreign key(type) references type(user_type)
);

create table course (
	cid varchar(6),
    teacher int,
    title varchar(70),
    credit tinyint,
    semester char(6) not null,
    year year not null,
    day enum('Monday','Tuesday','Wednesday','Thursday','Friday'),
    start_time time,
    duration tinyint,
    prerequisite varchar(5) default null,
    quota tinyint default 20,
    department_id varchar(2),
    primary key(cid),
    foreign key(teacher) references user(uid) ,
	foreign key(prerequisite) references course(cid) on delete set null on update cascade,
    foreign key(department_id) references department(did)
);


create table schedule(
    user_id int,
    course_id varchar(6),
    year year,
    dayIndex tinyint,
    timeIndex tinyint,
    primary key(user_id,year,dayIndex,timeIndex),
    foreign key(user_id) references user(uid),
	foreign key(course_id) references course(cid)
);


create table takes(
	tid int auto_increment,
    student_id int,
    course_id varchar(5),
    success boolean,
    foreign key(student_id) references user(uid),
    foreign key(course_id) references course(cid),
    primary key(tid)
);

create table notes(
	user_id int,
    course_id varchar(6),
    year year,
    grade varchar(2),
    primary key(user_id, course_id),
    foreign key(user_id) references user(uid),
    foreign key(course_id) references course(cid)
); 

