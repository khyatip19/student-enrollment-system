package com.khyati.student_enrollment.mappers;

import com.khyati.student_enrollment.dtos.EnrollmentResponseDTO;
import com.khyati.student_enrollment.dtos.EnrollmentUpdateRequest;
import com.khyati.student_enrollment.model.Enrollment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface EnrollmentMapper {
    EnrollmentResponseDTO toEnrollmentResponse(Enrollment enrollment);
}
