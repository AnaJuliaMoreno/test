package com.example.les11model.repository;

import com.example.les11model.model.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;


public interface LessonRepository extends JpaRepository<Lesson, Long> {
}
