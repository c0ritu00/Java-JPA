package com.jpa_example.spring.data.jpa.tutorial.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseId;
    private String name;
    private String description;
    private Integer credit;

    @OneToOne(
            mappedBy = "course"
    )
    private CourseMaterial courseMaterial;

    @ManyToOne(
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "teacher_id",
            referencedColumnName = "teacherId"
    )
    private Teacher teacher;

    @ManyToMany(
            cascade = CascadeType.ALL
    )
    @JoinTable(
            name = "student_course_map",
            joinColumns = @JoinColumn(
                    name = "course_id",
                    referencedColumnName = "courseId"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "student_id",
                    referencedColumnName = "studentId"
            )
    )
    private List<Student> students;

    public void addStudents(Student student){
        if(students == null) students = new ArrayList<>();
        students.add(student);
    }
}
