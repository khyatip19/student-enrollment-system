package com.khyati.student_enrollment.dtos;

import lombok.Data;

@Data
public class EnrollmentRequest {
    private Long studentId;
    private Long courseId;
}
