package com.jpa_example.spring.data.jpa.tutorial;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.jpa_example.spring.data.jpa.tutorial.entity.Course;
import com.jpa_example.spring.data.jpa.tutorial.entity.CourseMaterial;
import com.jpa_example.spring.data.jpa.tutorial.entity.Student;
import com.jpa_example.spring.data.jpa.tutorial.entity.Teacher;
import com.jpa_example.spring.data.jpa.tutorial.repository.CourseMaterialRepository;
import com.jpa_example.spring.data.jpa.tutorial.repository.CourseRepository;
import com.jpa_example.spring.data.jpa.tutorial.repository.StudentRepository;

@SpringBootApplication
public class SpringDataJpaTutorialApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringDataJpaTutorialApplication.class, args);
	}

        @Bean
        public CommandLineRunner loadData(CourseMaterialRepository courseMaterialRepository, StudentRepository studentRepository, CourseRepository courseRepository) {
        return (args) -> {
			saveCourseMaterialWithCourseAndStudentAndTeacher(courseMaterialRepository);
			saveCourseToExistingStudent(studentRepository, courseRepository);
		};
	}


	private void saveCourseToExistingStudent(StudentRepository studentRepository, CourseRepository courseRepository) {
		Student student = Student.builder()
                .firstName("Matti")
                .lastName("Massimies")
                .emailId("MattiMassimies@gmail.com")
                .build();

                Student student2 = Student.builder()
                .firstName("Testi")
                .lastName("Testaaja")
                .emailId("TestiTestaaja@gmail.com")
                .build();

		Course course = Course.builder()
                .name("Android-kurssi")
                .description("Sovellusten toteuttaminen Android-alustalle Java- ja XML-kieliä käytten.")
                .credit(3)
                .build();

                course.addStudents(student);
                course.addStudents(student2);
		student.addCourse(course);
		//studentRepository.save(student);
                courseRepository.save(course);
	}

	public void saveCourseMaterialWithCourseAndStudentAndTeacher(CourseMaterialRepository courseMaterialRepository) {

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

        CourseMaterial courseMaterial =
        CourseMaterial.builder()
        .url("www.moodle.oulu.fi")
        .course(course)
        .build();

        course.addStudents(student);

        courseMaterialRepository.save(courseMaterial);
    }
}
