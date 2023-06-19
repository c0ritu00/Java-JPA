package com.jpa_example.spring.data.jpa.tutorial.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long teacherId;
    private String firstName;
    private String lastName;

    @OneToMany(
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "teacher_id",
            referencedColumnName = "teacherId"
            
    )
    private List<Course> courses;
}
