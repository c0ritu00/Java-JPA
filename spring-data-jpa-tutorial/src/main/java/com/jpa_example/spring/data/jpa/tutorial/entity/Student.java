package com.jpa_example.spring.data.jpa.tutorial.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Table(
        name = "student",
        uniqueConstraints = @UniqueConstraint(
                name = "emailid_unique",
                columnNames = "email_address"
        )
)
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studentId;
    private String firstName;
    private String lastName;

    @Column(
            name = "email_address",
            nullable = false
    )
    private String emailId;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "students")
    private List<Course> courses;

    public void addCourse(Course course) {
        if (courses == null) {
            courses = new ArrayList<>();
        }
        courses.add(course);
    }
}
