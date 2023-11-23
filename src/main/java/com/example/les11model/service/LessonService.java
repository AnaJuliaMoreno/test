package com.example.les11model.service;

import com.example.les11model.dto.LessonDto;
import com.example.les11model.model.Lesson;
import com.example.les11model.repository.LessonRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LessonService {
    private final LessonRepository lessonRepos;

    public LessonService(LessonRepository lessonRepos) {
        this.lessonRepos = lessonRepos;
    }

    public LessonDto createLesson(LessonDto ldto){
    //mapping from dto to entity
        Lesson l = new Lesson();
        l.setTopic(ldto.topic);

        lessonRepos.save(l);
        ldto.id = l.getId();

        return  ldto;

        //
        //return l.getId();
    }

    public LessonDto getLesson(Long id){
        Lesson l =lessonRepos.findById(id).get();
        //mapping from entity to Dto
        LessonDto ldto = new LessonDto();
        ldto.topic=l.getTopic();

        return ldto;
    }

    public List<LessonDto> getAllLessons(){
        Iterable<Lesson> lessons =lessonRepos.findAll();

        List<LessonDto> lessonDtos = new ArrayList<>();
        for(Lesson l : lessons) {
            LessonDto ldto = new LessonDto();
            ldto.id =l.getId();
            ldto.topic=l.getTopic();
            lessonDtos.add(ldto);
        }


        return lessonDtos;
    }


}
