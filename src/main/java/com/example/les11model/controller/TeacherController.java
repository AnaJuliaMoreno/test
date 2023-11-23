package com.example.les11model.controller;

import com.example.les11model.dto.TeacherDto;
import com.example.les11model.model.Teacher;
import com.example.les11model.repository.TeacherRepository;
import com.example.les11model.service.TeacherService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("teachers")
public class TeacherController {

    //Dependency injection
    private final TeacherService service;

    public TeacherController(TeacherService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<TeacherDto>> getAllTeachers() {
        List<TeacherDto> teacherDtos = service.getAllTeachers();
        return ResponseEntity.ok(teacherDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeacherDto> getTeacher(@PathVariable Long id) {
        TeacherDto tdto = service.getTeacher(id);

        return ResponseEntity.ok(tdto);
    }
    //    @GetMapping("/after")
//    public ResponseEntity<List<Teacher>> getTeachersAfter(@RequestParam LocalDate date) {
//        return ResponseEntity.ok(repos.findByDobAfter(date));
//    }

    @PostMapping
    public ResponseEntity<Object> createTeacher(@Valid @RequestBody TeacherDto tdto, BindingResult br) {

        if (br.hasFieldErrors()) {
            StringBuilder sb = new StringBuilder();
            for (FieldError fe : br.getFieldErrors()) {
                sb.append(fe.getField());
                sb.append(" : ");
                sb.append(fe.getDefaultMessage());
                sb.append("\n");
            }
            return ResponseEntity.badRequest().body(sb.toString());

        } else {
            service.createTeacher(tdto);

            URI uri = URI.create(ServletUriComponentsBuilder
                    .fromCurrentRequest().path("/" + tdto.id).toUriString());


            return ResponseEntity.created(uri).body(tdto);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TeacherDto> deleteTeacher(@PathVariable Long id) {
        service.deleteTeacher(id);
        return ResponseEntity.noContent().build();
    }
// first the interface was made, so it would recognize findByDobBefore
//    @GetMapping("/before")
//    public ResponseEntity<Iterable<Teacher>> getTeachersbefore(@RequestParam LocalDate date) {
//        return ResponseEntity.ok(repos.findByDobBefore(date));
//    }
}
