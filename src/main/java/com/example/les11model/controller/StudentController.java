package com.example.les11model.controller;

import com.example.les11model.model.Student;
import com.example.les11model.model.Teacher;
import com.example.les11model.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequestMapping("students")
public class StudentController {
    @Autowired
    StudentRepository repos;

    @GetMapping
    public ResponseEntity<Iterable<Student>> getStudents() {
        return ResponseEntity.ok(repos.findAll());
    }

    @PostMapping
    public ResponseEntity<Student> createTeacher(@RequestBody Student s) {
        repos.save(s);

        URI uri = URI.create(ServletUriComponentsBuilder
                .fromCurrentRequest().path("/" + s.getId()).toUriString());

        return ResponseEntity.created(uri).body(s);
    }

    @GetMapping("/{name}")
    public ResponseEntity<Iterable<Student>> getStudentContaining(@RequestParam String name) {
        return ResponseEntity.ok(repos.findByFirstNameContaining(name));
    }
    //     In POSTMAN "localhost:8080/students/firstName?name=..." followed by the wanted name

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Student>> getStudent(@PathVariable Long id) {
        return ResponseEntity.ok(repos.findById(id));
    }
    //  In POSTMAN "localhost:8080/students/... followed by the wanted id

    @DeleteMapping("/{id}")
    public ResponseEntity<Student> deleteStudentById(@PathVariable Long id) {
        repos.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

