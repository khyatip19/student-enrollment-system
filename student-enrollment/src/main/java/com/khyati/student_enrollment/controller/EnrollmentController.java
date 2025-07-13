package com.khyati.student_enrollment.controller;

import com.khyati.student_enrollment.dtos.EnrollmentRequest;
import com.khyati.student_enrollment.dtos.EnrollmentResponseDTO;
import com.khyati.student_enrollment.dtos.EnrollmentUpdateRequest;
import com.khyati.student_enrollment.mappers.EnrollmentMapper;
import com.khyati.student_enrollment.model.Course;
import com.khyati.student_enrollment.model.Enrollment;
import com.khyati.student_enrollment.model.Student;
import com.khyati.student_enrollment.repository.CourseRepository;
import com.khyati.student_enrollment.repository.EnrollmentRepository;
import com.khyati.student_enrollment.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private EnrollmentMapper enrollmentMapper;

    @PostMapping
    public ResponseEntity<EnrollmentResponseDTO> enrollStudent(@RequestBody EnrollmentRequest request){
        Student student = studentRepository.findById(request.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Course course = courseRepository.findById(request.getCourseId())
                .orElseThrow(() -> new RuntimeException("Course not found"));

        Enrollment enrollment = Enrollment.builder()
                .student(student)
                .course(course)
                .build();

        return ResponseEntity.ok(enrollmentMapper.toEnrollmentResponse(enrollmentRepository.save(enrollment)));
    }

    // to get all products no filters
    @GetMapping
    public List<EnrollmentResponseDTO> getFilteredEnrollments(
            @RequestParam(required = false) Long studentId,
            @RequestParam(required = false) Long courseId) {
        List <Enrollment> enrollments;

        if (studentId != null && courseId != null){
            enrollments = enrollmentRepository.findByStudentIdAndCourseId(studentId, courseId);
        } else if(studentId != null){
            enrollments = enrollmentRepository.findByStudentId(studentId);
        } else if(courseId != null){
            enrollments = enrollmentRepository.findByCourseId(courseId);
        } else {
            enrollments = enrollmentRepository.findAll();
        }
        return enrollments.stream()
                .map(enrollmentMapper::toEnrollmentResponse)
                .collect(Collectors.toList());
    }
    // Get by enrollment1?
    @GetMapping("/{id}")
    public ResponseEntity<EnrollmentResponseDTO> getEnrollmentById(@PathVariable Long id){
        return enrollmentRepository.findById(id)
                .map(enrollmentMapper::toEnrollmentResponse)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());

    }

    // Put mapping for updating student and course enrollments
    @PutMapping("/{id}")
    public ResponseEntity<EnrollmentResponseDTO> updateEnrollment(@PathVariable Long id,
                                                                  @RequestBody EnrollmentUpdateRequest updateRequest){
        // First fetch the entry using id
        Enrollment enrollment = enrollmentRepository.findById(id).orElse(null);
        if (enrollment == null){
            return ResponseEntity.notFound().build();
        }

        // Update enrollment if found
        Course course = courseRepository.findById(updateRequest.getCourseId())
                        .orElseThrow(() -> new RuntimeException("Course not found"));

        Student student = studentRepository.findById(updateRequest.getStudentId())
                        .orElseThrow(() -> new RuntimeException("Student not found"));

        enrollment.setCourse(course);
        enrollment.setStudent(student);
        Enrollment updatedEnrollment = enrollmentRepository.save(enrollment);

        return ResponseEntity.ok(enrollmentMapper.toEnrollmentResponse(updatedEnrollment));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<EnrollmentResponseDTO> patchEnrollment(@PathVariable Long id,
                                                                 @RequestBody EnrollmentUpdateRequest updateRequest){
        Enrollment enrollment = enrollmentRepository.findById(id).orElse(null);
        if (enrollment == null){
            return ResponseEntity.notFound().build();
        }

        if (updateRequest.getStudentId() != null){
            Student student = studentRepository.findById(updateRequest.getStudentId())
                    .orElseThrow(() -> new RuntimeException("Student not found"));
            enrollment.setStudent(student);
        }


        if (updateRequest.getCourseId() != null){
            Course course = courseRepository.findById(updateRequest.getCourseId())
                    .orElseThrow(() -> new RuntimeException("Course not found"));
            enrollment.setCourse(course);
        }
        enrollmentRepository.save(enrollment);

        return ResponseEntity.ok(enrollmentMapper.toEnrollmentResponse(enrollment));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEnrollment(@PathVariable Long id){
        if (!enrollmentRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        enrollmentRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
