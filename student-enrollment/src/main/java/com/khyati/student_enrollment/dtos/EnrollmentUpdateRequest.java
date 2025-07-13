package com.khyati.student_enrollment.dtos;

import lombok.Data;

@Data
public class EnrollmentUpdateRequest {
    // Only admins can update enrollments
    // Can edit student enrollments --> change course for a student
    private Long studentId;
    private Long courseId;
}
