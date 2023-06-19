package com.jpa_example.spring.data.jpa.tutorial.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import com.jpa_example.spring.data.jpa.tutorial.entity.Course;
import com.jpa_example.spring.data.jpa.tutorial.entity.CourseMaterial;

import java.util.List;

@SpringBootTest
@DataJpaTest    //  <- Flushes data after test
class CourseMaterialRepositoryTest {

    @Autowired
    private CourseMaterialRepository repository;

    @Test
    public void SaveCourseMaterial() {
        Course course =
                Course.builder()
                .name("Tutkimus- ja kehittämistoiminta")
                .description("Oman ammattialan tutkimus- ja kehittämistoiminta.")
                .credit(3)
                .build();

        CourseMaterial courseMaterial =
                CourseMaterial.builder()
                .url("www.moodle.oulu.fi")
                .course(course)
                .build();

        repository.save(courseMaterial);
    }
    
    @Test
    public void printAllCourseMaterials() {
        List<CourseMaterial> courseMaterials = 
                repository.findAll();

        System.out.println("courseMaterials = " + courseMaterials);
    }
}