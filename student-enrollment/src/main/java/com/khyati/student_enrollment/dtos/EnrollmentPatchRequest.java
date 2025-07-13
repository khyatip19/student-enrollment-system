package com.khyati.student_enrollment.dtos;

import lombok.Data;

@Data
public class EnrollmentPatchRequest {
    private Long studentId;
    private Long courseId;
}
