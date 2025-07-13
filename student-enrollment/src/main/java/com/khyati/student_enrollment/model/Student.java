package com.khyati.student_enrollment.model;

import jakarta.persistence.*;
import lombok.*;

@Entity //tells spring this class maps to a DB table
@Data   // generates getters/setters automatically
@NoArgsConstructor //creates a default constructor
@AllArgsConstructor // creates a constructor with all fields
@Builder // allows using Builder pattern to create objects
@Table(name = "students") // sets table name explicitly
public class Student {
    @Id // marks the following key as primary key (id in this case)
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto-increment
    private Long id;

    private String name;
    private String email;
}