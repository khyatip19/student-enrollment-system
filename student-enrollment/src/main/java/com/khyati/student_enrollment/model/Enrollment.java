package com.khyati.student_enrollment.model;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "enrollments")
public class Enrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Foreign key to Student
    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;
    // we use object like student, instead of IDs, so we can directly access full student details.

    // Foreign key to Course
    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

}
