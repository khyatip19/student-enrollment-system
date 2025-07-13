package com.khyati.student_enrollment.mappers;

import com.khyati.student_enrollment.dtos.CourseResponseDTO;
import com.khyati.student_enrollment.dtos.CourseUpdateRequest;
import com.khyati.student_enrollment.model.Course;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CourseMapper {
    CourseResponseDTO toCourseResponse(Course course);
    // Update existing course from DTO
    void updateCourse(CourseUpdateRequest request, @MappingTarget Course course);
}
