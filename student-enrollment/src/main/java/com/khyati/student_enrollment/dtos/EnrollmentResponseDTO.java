package com.khyati.student_enrollment.dtos;

import lombok.Data;

@Data
public class EnrollmentResponseDTO {
    private Long id;
    private StudentResponseDTO student;
    private CourseResponseDTO course;
}

