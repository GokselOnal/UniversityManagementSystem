# University Management System

![mainPage](https://user-images.githubusercontent.com/73059230/126874044-e8100556-c423-41d9-8ab3-eb1b4c771aad.png)


This Project is created for Goksel University. System keeps information about students, teachers and so on. <br>
There are three types of account;

1) Admin
2) Teacher
3) Student

Admin can manage the whole system by his/her account. Students and teachers accounts are created by the admin, they cannot register the system.

Admin can view, update and add new information specified below. <br>
  - Program
  - Course
  - Teacher
  - Student

---

# Admin


  
Username: admin <br>
Password: aa

### Master 
  
Admin can add new items to system. Admin can add new program or new course and also can admit new students or teachers.

#### <i>New Program</i>
  - Department Id and Department Name fields must be filled.<br>
  - Department Id and Department Name fields must be unique.<br>
  - Department Id must have maximum 3 characters.<br>
  
#### <i>New Course</i>
  - All input fields must be filled.
  - Course Id must have maximum 5 characters.
  - Title must be given.
  - Teacher must be selected.
  - Credit must be selected.
  - Semester must be selected.
  - Year must be specified.
  - Day must be selected.
  - Start time and Duration must be selected.
  - If the course wanted to be added has a prerequisite course, prerequisite course must be selected. Otherwise (None) must be selected.
  - Deparment Id must be selected.
  - Course Id must be unique.
  - Specified year, semester, time period must be available to add this new course.
  - When a new course is added to system, its quota is created 20 by default.

#### <i>New Teacher Admission</i>
  - All input fields must be filled.
  - Name, Age, E-mail, Address must be specified.
  - Phone must have 11 characters.
  - Date of Birthday must be specified that format yyyy-mm-dd.
  - Department must be selected.
  - Name of the teacher will be username of his/her account.
  - Password must be written.

#### <i>New Student Admission</i>
  - All input fields must be filled.
  - Name, Age, E-mail, Address must be specified.
  - Phone must have 11 characters.
  - Date of Birthday must be specified that format yyyy-mm-dd.
  - Level(Undergraduate, Master, PhD) must be selected.
  - Name of the student will be username of his/her account.
  - Password must be written.

---
  
### Update

Admin can update information in the system.

#### <i>Update Program</i>
  - Name of the programs can be changed.
  - Existed programs can be deleted.
  
#### <i>Update Course</i>
  - Quota of courses can be changed.
  - Teacher of courses can be changed.
  - Day of courses can be changed.
  - Start time of courses can be changed.
  - Existed courses can be deleted.
  - Day or start time field can be changed if the desired day or start time is available in the system.
  - Teacher can be changed if the program of new teacher is available.

#### <i>Update Teacher</i>
  - Program of teacher can be changed.
  - E-mail of teacher can be changed.
  - Phone number of teacher can be changed.
  - Teacher can be deleted.
 
#### <i>Update Student</i>
  - Program of student can be changed.
  - Level of student can be changed.
  - E-mail of student can be changed.
  - Phone number of student can be changed.
  - Student can be deleted.

---
  
### Details

Admin can see detailed information.<br>

#### <i>Program Details</i>
  - Name of programs.
  - Id of programs.
  
#### <i>Course Details</i>
  - Course Id, title, department courses.
  - Teacher name of courses.
  - Credit of courses.
  - Year, semester, day, start time and duration of courses.
  - Prerequisites of courses.
  - Quota of courses.
  - Filtering metod can be used for advance searching.
  - Items can be filtered according to year, semester, credit and department.
  
#### <i>Teacher Details</i>
  - Name of teachers.
  - Age of teachers.
  - E-mail of teachers.
  - Address of teachers.
  - Phone number of teachers.
  - Birthdate of teachers.
  - Program of teachers.
  - Items can be filtered according to department of teachers.
  
#### <i>Student Details</i>
  - Name of students.
  - Age of students.
  - E-mail of students.
  - Address of students.
  - Phone number of students.
  - Birthdate of students.
  - Level of students.
  - Program of students.
  - Items can be filtered according to level and department of students.
  


---

# Teacher

They can login the system with their name and given password. In teacher page, there are 4 sections.<br>

#### <i>Course Offered</i>
  - Teachers can see all courses with detailed information.
  - Teachers can make a search courses with filtering by year, semester, credit, department. 

#### <i>Examination</i>
  - Teachers can see their current semester courses and registered student lists of courses, teachers can enter students grades in this section. 

#### <i>My Schedule</i>
  - Teachers can see their current semester weekly programs in a table.

#### <i>Academic Information</i>
  - Teachers can see their current semester academic information such as course id, course name and number of registered student of their classes.

---

# Student

They can login the system with their name and given password. In student page, there are also 4 sections.<br>

#### <i>Course Offered</i>
  - Students can see all courses with detailed information.
  - Students can make a search courses with filtering by year, semester, credit, department. 

#### <i>Course Registration</i>
  - Course registration system is active only on determined days by admin.
  - It is not possible to enter this section except as specified dates.
  - For just before spring and fall semesters, the system is active 2 times in a year.
  - This section same as course offered, student can see courses and also can make an advance search to see courses. Additionally, they can register courses.
  - Students can register courses for their new academic semester.
  - Students can register courses, if the course has enough quota.
  - Students can register courses, if their weekly schedule is available in terms of courses time periods.
  - Students must have passed prerequisite courses successfully, if the course they are planning to register has prerequisite.


#### <i>My Schedule</i>
  - Students can see their current semester weekly programs in a table.

#### <i>Academic Information</i>
  - Students can see their GPA and academic standing (Honor, High Honor).
  - Students can see their transcript.
