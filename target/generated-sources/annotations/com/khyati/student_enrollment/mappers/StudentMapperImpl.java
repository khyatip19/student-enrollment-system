package com.khyati.student_enrollment.mappers;

import com.khyati.student_enrollment.dtos.StudentResponseDTO;
import com.khyati.student_enrollment.dtos.StudentUpdateRequest;
import com.khyati.student_enrollment.model.Student;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-12T21:58:26-0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 24.0.1 (Oracle Corporation)"
)
@Component
public class StudentMapperImpl implements StudentMapper {

    @Override
    public StudentResponseDTO toStudentResponse(Student student) {
        if ( student == null ) {
            return null;
        }

        StudentResponseDTO studentResponseDTO = new StudentResponseDTO();

        studentResponseDTO.setId( student.getId() );
        studentResponseDTO.setName( student.getName() );
        studentResponseDTO.setEmail( student.getEmail() );

        return studentResponseDTO;
    }

    @Override
    public void updateStudent(StudentUpdateRequest request, Student student) {
        if ( request == null ) {
            return;
        }

        student.setName( request.getName() );
        student.setEmail( request.getEmail() );
    }
}
