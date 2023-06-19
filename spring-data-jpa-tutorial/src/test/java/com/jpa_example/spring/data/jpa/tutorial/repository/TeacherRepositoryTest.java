package com.jpa_example.spring.data.jpa.tutorial.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.jpa_example.spring.data.jpa.tutorial.entity.Course;
import com.jpa_example.spring.data.jpa.tutorial.entity.Teacher;


import java.util.List;



@SpringBootTest
class TeacherRepositoryTest {

    @Autowired
    private TeacherRepository teacherRepository;

    @Test
    public void saveTeacher() {

        Course coursePilvi = Course.builder()
                .name("Pilvipalvelut")
                .description("Modernit pilvipalvelutekniikat, -ominaisuudet ja käyttökohteet.")
                .credit(5)
                .build();

        Course courseJava = Course.builder()
                .name("Java-ohjelmointi")
                .description("Java-ohjelmoinnin perusteet")
                .credit(5)
                .build();

        Teacher teacher =
                Teacher.builder()
                        .firstName("Kari")
                        .lastName("Aho")
                        .courses(List.of(coursePilvi, courseJava))
                        .build();

        teacherRepository.save(teacher);
    }
}