package ru.itis.spring.app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.itis.spring.config.JpaConfig;
import ru.itis.spring.repositories.StudentRepository;

public class Application {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(JpaConfig.class);
        StudentRepository repository = context.getBean(StudentRepository.class);
        System.out.println();
    }
}
