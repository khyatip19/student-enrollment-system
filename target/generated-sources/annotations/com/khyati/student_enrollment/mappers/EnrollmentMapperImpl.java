package com.khyati.student_enrollment.mappers;

import com.khyati.student_enrollment.dtos.CourseResponseDTO;
import com.khyati.student_enrollment.dtos.EnrollmentResponseDTO;
import com.khyati.student_enrollment.dtos.StudentResponseDTO;
import com.khyati.student_enrollment.model.Course;
import com.khyati.student_enrollment.model.Enrollment;
import com.khyati.student_enrollment.model.Student;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-12T23:01:05-0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 24.0.1 (Oracle Corporation)"
)
@Component
public class EnrollmentMapperImpl implements EnrollmentMapper {

    @Override
    public EnrollmentResponseDTO toEnrollmentResponse(Enrollment enrollment) {
        if ( enrollment == null ) {
            return null;
        }

        EnrollmentResponseDTO enrollmentResponseDTO = new EnrollmentResponseDTO();

        enrollmentResponseDTO.setId( enrollment.getId() );
        enrollmentResponseDTO.setStudent( studentToStudentResponseDTO( enrollment.getStudent() ) );
        enrollmentResponseDTO.setCourse( courseToCourseResponseDTO( enrollment.getCourse() ) );

        return enrollmentResponseDTO;
    }

    protected StudentResponseDTO studentToStudentResponseDTO(Student student) {
        if ( student == null ) {
            return null;
        }

        StudentResponseDTO studentResponseDTO = new StudentResponseDTO();

        studentResponseDTO.setId( student.getId() );
        studentResponseDTO.setName( student.getName() );
        studentResponseDTO.setEmail( student.getEmail() );

        return studentResponseDTO;
    }

    protected CourseResponseDTO courseToCourseResponseDTO(Course course) {
        if ( course == null ) {
            return null;
        }

        CourseResponseDTO courseResponseDTO = new CourseResponseDTO();

        courseResponseDTO.setId( course.getId() );
        courseResponseDTO.setTitle( course.getTitle() );
        courseResponseDTO.setDescription( course.getDescription() );

        return courseResponseDTO;
    }
}
