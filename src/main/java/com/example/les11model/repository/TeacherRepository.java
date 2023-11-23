package com.example.les11model.repository;

import com.example.les11model.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    // to get teachers that have been born for example before a date.
    // to avoid asking for the whole list
    List<Teacher> findByDobBefore(LocalDate date);

    List<Teacher> findByFirstName(String firstName);
}
