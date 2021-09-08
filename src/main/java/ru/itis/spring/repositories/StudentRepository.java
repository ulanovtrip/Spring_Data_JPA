package ru.itis.spring.repositories;


import ru.itis.spring.models.Student;

public interface StudentRepository {

    void save(Student student);
}
