package com.khyati.student_enrollment.dtos;

import lombok.Data;


// Here we annotate with data because we want getters and setters of Student
// @Data generates - getName(), getEmail(), setEmail(), setName(), toString() and equals()
@Data
public class StudentUpdateRequest {
    private String name;
    private String email;
}
