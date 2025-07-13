//package com.khyati.student_enrollment.bootstrap;
//
//import com.khyati.student_enrollment.model.Student;
//import com.khyati.student_enrollment.repository.StudentRepository;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//@Component
//public class DataInitializer implements CommandLineRunner {
//    private final StudentRepository studentRepository;
//
//    public DataInitializer(StudentRepository studentRepository){
//        this.studentRepository = studentRepository;
//    }
//
//    @Override
//    public void run(String... args) throws Exception{
//        // Create student object
//        Student student = Student.builder()
//                .name("Khyati")
//                .email("khyati@gmail.com")
//                .build();
//
//        studentRepository.save(student);
//        System.out.println("Student saved: " +student);
//    }
//}
