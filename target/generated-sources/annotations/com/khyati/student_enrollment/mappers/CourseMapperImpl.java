package com.khyati.student_enrollment.mappers;

import com.khyati.student_enrollment.dtos.CourseResponseDTO;
import com.khyati.student_enrollment.dtos.CourseUpdateRequest;
import com.khyati.student_enrollment.model.Course;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-12T21:58:26-0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 24.0.1 (Oracle Corporation)"
)
@Component
public class CourseMapperImpl implements CourseMapper {

    @Override
    public CourseResponseDTO toCourseResponse(Course course) {
        if ( course == null ) {
            return null;
        }

        CourseResponseDTO courseResponseDTO = new CourseResponseDTO();

        courseResponseDTO.setId( course.getId() );
        courseResponseDTO.setTitle( course.getTitle() );
        courseResponseDTO.setDescription( course.getDescription() );

        return courseResponseDTO;
    }

    @Override
    public void updateCourse(CourseUpdateRequest request, Course course) {
        if ( request == null ) {
            return;
        }

        course.setTitle( request.getTitle() );
        course.setDescription( request.getDescription() );
    }
}
