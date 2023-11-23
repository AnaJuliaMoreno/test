insert into teachers(id, first_Name, last_Name, dob, salary)
values (nextval('teachers_seq'), 'Piet', 'Peterson', '2001-05-21', 700);

insert into students(id, first_Name, last_name, phone_Number)
values (nextval('students_seq'), 'Sandra', 'Jansen', '063216534');

insert into courses(id, title, study_points)
values (nextval('courses_seq'), 'Java', 10);

insert into lessons(id, topic)
values (nextval('lessons_seq'), 'Polymorphism')
