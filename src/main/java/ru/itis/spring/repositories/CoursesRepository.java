package ru.itis.spring.repositories;



import ru.itis.spring.models.Course;

import java.util.List;

public interface CoursesRepository {

    void save(Course course);

    List<Course> findAllByStudentsCount(int count);
}
