package com.khyati.student_enrollment.dtos;

import lombok.Data;

@Data
public class StudentPatchRequest {
    private String name;
    private String email;
}
