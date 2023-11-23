package com.example.les11model.dto;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TeacherDto {
    public Long id;
    @NotBlank
    public String firstName;

    @Size(min = 3, max = 255)
    public String lastName;

    @Past (message = "must be a date in the past")
    public LocalDate dob;

    @Min(0)
    @Max(value = 1000)
    public int salary;

    public List<String> courseTitles = new ArrayList<>();
}
