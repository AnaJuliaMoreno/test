package com.example.les11model.controller;

import com.example.les11model.dto.LessonDto;
import com.example.les11model.service.LessonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("lessons")
public class LessonController {
    private final LessonService service;
    public LessonController(LessonService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<LessonDto> getLesson(@PathVariable Long id){
        LessonDto lDto = service.getLesson(id);
        return ResponseEntity.ok(lDto);
    }

    @GetMapping
    ResponseEntity<List<LessonDto>> getAllLessons() {
        List<LessonDto> lessonDtos = service.getAllLessons();
        return ResponseEntity.ok(lessonDtos);
    }
    @PostMapping
    public ResponseEntity<Object> createLesson(@RequestBody LessonDto ldto) {
        //Long id =
                service.createLesson(ldto);
       // ldto.id = id;

        URI uri = URI.create(ServletUriComponentsBuilder
                .fromCurrentRequest().path("/" + ldto.id).toUriString());

        return ResponseEntity.created(uri).body(ldto);
    }

}
