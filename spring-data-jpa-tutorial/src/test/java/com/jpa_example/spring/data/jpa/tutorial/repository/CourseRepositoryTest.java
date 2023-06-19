package com.jpa_example.spring.data.jpa.tutorial.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.jpa_example.spring.data.jpa.tutorial.entity.Course;
import com.jpa_example.spring.data.jpa.tutorial.entity.Student;
import com.jpa_example.spring.data.jpa.tutorial.entity.Teacher;

import java.util.List;

@SpringBootTest
class CourseRepositoryTest {

    @Autowired
    private CourseRepository courseRepository;
    
    @Test
    public void printCourses() {
        List<Course> courses =
                courseRepository.findAll();

        System.out.println("courses = " + courses);
    }

    @Test
    public void saveCourseWithTeacher() {
        Teacher teacher = Teacher.builder()
                .firstName("Reima")
                .lastName("Kallio")
                .build();

        Course course = Course
                .builder()
                .name("Web-ohjelmoinnin sovellusprojekti")
                .description("Sovellusprojekti valinnaisilla työkaluilla")
                .credit(3)
                .teacher(teacher)
                .build();

        courseRepository.save(course);
    }


    @Test
    public void saveCourseWithStudentAndTeacher() {

        Teacher teacher = Teacher.builder()
                .firstName("Elina")
                .lastName("Niemi")
                .build();

        Student student = Student.builder()
                .firstName("Kari")
                .lastName("Kandi")
                .emailId("KariKandi@gmail.com")
                .build();

        Course course = Course
                .builder()
                .name("Mobiiliohjelmointi natiiviteknologioilla")
                .description("Sovellusten toteuttaminen Android-alustalle Java- ja XML-kieliä käytten.")
                .credit(4)
                .teacher(teacher)
                .build();

        course.addStudents(student);

        courseRepository.save(course);
    }

}