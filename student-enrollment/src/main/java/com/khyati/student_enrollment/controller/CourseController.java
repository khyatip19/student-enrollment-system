package com.khyati.student_enrollment.controller;

import com.khyati.student_enrollment.dtos.CoursePatchRequest;
import com.khyati.student_enrollment.dtos.CourseResponseDTO;
import com.khyati.student_enrollment.dtos.CourseUpdateRequest;
import com.khyati.student_enrollment.dtos.StudentUpdateRequest;
import com.khyati.student_enrollment.mappers.CourseMapper;
import com.khyati.student_enrollment.model.Course;
import com.khyati.student_enrollment.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CourseMapper courseMapper;

    // POST /api/courses
    @PostMapping
    public Course createCourse(@RequestBody Course course){
        return courseRepository.save(course);
    }

    // GET /api/courses
    @GetMapping
    public List<Course> getAllCourses(){
        return courseRepository.findAll();
    }

    // GET by ID /api/courses/id
    @GetMapping("/{id}")
    public ResponseEntity<CourseResponseDTO> getCourseById(@PathVariable Long id){
        var course = courseRepository.findById(id)
                .orElse(null);
        if (course == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(courseMapper.toCourseResponse(course));

    }

    // PUT request - PUT /api/courses/id
    @PutMapping("/{id}")
    public ResponseEntity<CourseResponseDTO> updateCourse(@PathVariable Long id,
                                                          @RequestBody CourseUpdateRequest updateRequest){
        // First we will fetch the id
        Course course = courseRepository.findById(id).orElse(null);
        if (course == null){
            return ResponseEntity.notFound().build();
        }

        // If course is present, we want to edit the details
        // No need of manual mapping because mapper can be used in this case!
//        course.setTitle(updateRequest.getTitle());
//        course.setDescription(updateRequest.getDescription());

        courseMapper.updateCourse(updateRequest, course);

        courseRepository.save(course);

        return ResponseEntity.ok(courseMapper.toCourseResponse(course));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CourseResponseDTO> patchCourse(@PathVariable Long id,
                                                         @RequestBody CoursePatchRequest patchRequest){
        Course course = courseRepository.findById(id).orElse(null);
        if (course == null){
            return ResponseEntity.notFound().build();
        }

        if (patchRequest.getTitle() != null){
            course.setTitle(patchRequest.getTitle());
        }

        if (patchRequest.getDescription() != null){
            course.setDescription(patchRequest.getDescription());
        }

        courseRepository.save(course);
        return ResponseEntity.ok(courseMapper.toCourseResponse(course));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id){
        if (!courseRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        courseRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
