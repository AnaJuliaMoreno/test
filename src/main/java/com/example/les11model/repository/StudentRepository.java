package com.example.les11model.repository;

import com.example.les11model.model.Student;
import org.springframework.data.repository.CrudRepository;



public interface StudentRepository extends CrudRepository<Student, Long> {

    Iterable<Student> findByFirstNameContaining(String name);

}
