package com.khyati.student_enrollment.dtos;

import lombok.Data;

@Data
public class CourseUpdateRequest {
    private String title;
    private String description;
}
