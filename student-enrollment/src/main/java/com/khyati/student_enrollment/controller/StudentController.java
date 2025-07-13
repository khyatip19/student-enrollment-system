package com.khyati.student_enrollment.controller;

import com.khyati.student_enrollment.dtos.StudentPatchRequest;
import com.khyati.student_enrollment.dtos.StudentResponseDTO;
import com.khyati.student_enrollment.dtos.StudentUpdateRequest;
import com.khyati.student_enrollment.mappers.StudentMapper;
import com.khyati.student_enrollment.model.Student;
import com.khyati.student_enrollment.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // handle HTTP requests and return JSON
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentMapper studentMapper;

    // POST /api/students
    @PostMapping
    public Student createStudent(@RequestBody Student student){
        return studentRepository.save(student);
    }

    // GET /api/students
    @GetMapping
    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }

    // GET by {id} /api/students/id
    // Return custom HTTP status codes
    @GetMapping("/{id}")
    public ResponseEntity<StudentResponseDTO> getStudentById(@PathVariable Long id){

        var student = studentRepository.findById(id)
                .orElse(null);

        if (student == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(studentMapper.toStudentResponse(student));
    }

    // Put mapping to indicate that its a PUT api,
    // create a new method called update student
    // we will need 2 parameters, one would be the compulsory id, to know which student to update
    // second will be the data (request body)
    // What should be use for request body?
    // Should we use Student object or Student Response DTO object?
    // Anything will work, but reluctance because we dont want client to send id.
    // so we will create new DTO

    // Implementation of PUT /api/students/{id}
    @PutMapping("/{id}")
    public ResponseEntity<StudentResponseDTO> updateStudent(@PathVariable Long id,
                                              @RequestBody StudentUpdateRequest updateRequest){

        // First, we need to find the student by the ID
        Student student = studentRepository.findById(id).orElse(null);

        if (student == null){
            return ResponseEntity.notFound().build();
        }
        // No need to manually update the student fields, let mapper do it for u.
        // If student is found, update fields
//        student.setName(updateRequest.getName());
//        student.setEmail(updateRequest.getEmail());

        studentMapper.updateStudent(updateRequest, student);

        // Save the updates that we made
        studentRepository.save(student);

        return ResponseEntity.ok(studentMapper.toStudentResponse(student));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<StudentResponseDTO> patchStudent(@PathVariable Long id,
                                                           @RequestBody StudentPatchRequest patchRequest){

        // Fetch by id
        Student student = studentRepository.findById(id).orElse(null);

        if (student == null){
            return ResponseEntity.notFound().build();
        }
        // if found, patch
        if (patchRequest.getName() != null){
            student.setName(patchRequest.getName());
        }
        if (patchRequest.getEmail() != null){
            student.setEmail(patchRequest.getEmail());
        }

        studentRepository.save(student);
        return ResponseEntity.ok(studentMapper.toStudentResponse(student));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id){
        if (!studentRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        studentRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
