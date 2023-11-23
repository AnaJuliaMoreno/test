package com.example.les11model.controller;

import com.example.les11model.dto.CourseDto;
import com.example.les11model.model.Course;
import com.example.les11model.model.Teacher;
import com.example.les11model.repository.CourseRepository;
import com.example.les11model.repository.TeacherRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("courses")
public class CourseController {

    private final CourseRepository cRepos;
    private final TeacherRepository teacherRepos;

    public CourseController(CourseRepository cRepos, TeacherRepository teacherRepos) {
        this.cRepos = cRepos;
        this.teacherRepos = teacherRepos;
    }

    @PostMapping
    public ResponseEntity<CourseDto> createCourse(@Valid @RequestBody CourseDto cdto) {
        Course course = new Course();
        //mapping from dto to entity
        course.setTitle(cdto.title);
        course.setStudyPoints(cdto.studyPoints);

        //find and add a teacher
        for (Long id: cdto.teacherIds) {
        Optional<Teacher> optionalTeacher = teacherRepos.findById(id);
        if (optionalTeacher.isPresent()) {
            course.getTeacher().add(optionalTeacher.get());
        }
        }
        cRepos.save(course);
        cdto.id = course.getId(); //after the object has been made it has an ID
        return new ResponseEntity<>(cdto, HttpStatus.CREATED);

    }
}
