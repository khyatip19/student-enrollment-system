package com.khyati.student_enrollment.mappers;

import com.khyati.student_enrollment.dtos.StudentResponseDTO;
import com.khyati.student_enrollment.dtos.StudentUpdateRequest;
import com.khyati.student_enrollment.model.Student;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface StudentMapper {
    StudentResponseDTO toStudentResponse(Student student);

    void updateStudent(StudentUpdateRequest request,@MappingTarget Student student);
}
