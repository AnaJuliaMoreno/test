package com.example.les11model.service;

import com.example.les11model.dto.TeacherDto;
import com.example.les11model.exception.ResourceNotFoundException;
import com.example.les11model.model.Course;
import com.example.les11model.model.Teacher;
import com.example.les11model.repository.TeacherRepository;
import org.springframework.stereotype.Service;

import java.sql.ClientInfoStatus;
import java.util.ArrayList;
import java.util.List;

@Service
public class TeacherService {

    //dependency injection
    private final TeacherRepository repos;

    public TeacherService(TeacherRepository repos) {
        this.repos = repos;
    }


    public TeacherDto createTeacher(TeacherDto tdto) {

       /* List<Teacher> existingTeacher = repos.findByFirstName(tdto.firstName);
        if (!existingTeacher.isEmpty()) {
            throw new DuplicateNameException("This name has already been used. Please enter a different name.");
        }*/

        //mapping from DTO to Entity
        Teacher t = new Teacher();

        t.setFirstName(tdto.firstName);
        t.setLastName(tdto.lastName);
        t.setDob(tdto.dob);
        t.setSalary(tdto.salary);
        repos.save(t);
        tdto.id = t.getId();

        return tdto;

/* to link the exception from teacher.java
        try {
            repos.save(t);
        }
        catch (Exception e) {
            throw new NameTooLongException("First name is too long. " +
                    "Must be less than 6 letters.");
        }

        return t.getId();*/
    }


    public TeacherDto getTeacher(Long id) {
        //Retrieves a Teacher object from the data repository 'repos'/Entity.
        Teacher t = repos.findById(id).orElseThrow(() -> new ResourceNotFoundException("Teacher not found"));

        // Mapping From Entity to DTO
        //1. Create a new instance of TeacherDto called tdto.
        //2. Get the data from the Teacher object and set it to the TeacherDto object.

        TeacherDto tdto = new TeacherDto();
        tdto.id = t.getId();
        tdto.firstName = t.getFirstName();
        tdto.lastName = t.getLastName();
        tdto.dob = t.getDob();
        tdto.salary = t.getSalary();

        for (Course c : t.getCourses()) {
            tdto.courseTitles.add(c.getTitle());
        }
        return tdto;
    }


    public List<TeacherDto> getAllTeachers() {
        List<Teacher> teachers = repos.findAll();


        List<TeacherDto> teacherDtos = new ArrayList<>();
        //create a list of TeacherDto objects where the to put the info later
        for (Teacher t : teachers) {
            TeacherDto tdto = new TeacherDto();
            tdto.id = t.getId();
            tdto.firstName = t.getFirstName();
            tdto.lastName = t.getLastName();
            tdto.dob = t.getDob();
            tdto.salary = t.getSalary();
            teacherDtos.add(tdto);
        }
        return teacherDtos;
    }
    public void deleteTeacher(Long id) {
    repos.deleteById(id);
    }



}
