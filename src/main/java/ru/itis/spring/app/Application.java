package ru.itis.spring.app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.itis.spring.config.JpaConfig;
import ru.itis.spring.models.Course;
import ru.itis.spring.models.Student;
import ru.itis.spring.repositories.CoursesRepository;
import ru.itis.spring.repositories.StudentRepository;

import java.util.Arrays;

public class Application {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(JpaConfig.class);
        CoursesRepository coursesRepository = context.getBean(CoursesRepository.class);
        StudentRepository studentRepository = context.getBean(StudentRepository.class);
        System.out.println();

        // создали курс
        Course java = Course.builder()
                .title("Java")
                .build();

        Course sql = Course.builder()
                .title("Sql")
                .build();

        // сохранили курсы
        coursesRepository.save(java);
        coursesRepository.save(sql);

        // создали студентов
        Student ivan = Student.builder()
                .firstName("Ivan")
                .lastName("Ulanov")
                .courses(Arrays.asList(java, sql))
                .build();

        Student nikola = Student.builder()
                .firstName("Nikola")
                .lastName("Ivanov")
                .courses(Arrays.asList(java, sql))
                .build();

        Student olga = Student.builder()
                .firstName("Olga")
                .lastName("Kozlov")
                .courses(Arrays.asList(java, sql))
                .build();

        // сохранили студентов
        studentRepository.save(ivan);
        studentRepository.save(nikola);
        studentRepository.save(olga);
    }
}
