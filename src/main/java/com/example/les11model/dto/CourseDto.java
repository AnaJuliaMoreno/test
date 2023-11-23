package com.example.les11model.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;

public class CourseDto {
    public Long id;

    @NotBlank
    public String title;

    @Max(value = 30)
    public int studyPoints;

    public long [] teacherIds;

    public List<String> lessonTopics = new ArrayList<>();


}
