package ru.itis.spring.repositories;


import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.spring.models.Course;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class CoursesRepositoryJpaImpl implements CoursesRepository {

    // стандарт JPA, через него можно работать с сущностями, это предоставит взаимодействие с БД
    @PersistenceContext
    private EntityManager entityManagerCourse;

    @Override
    @Transactional
    public void save(Course course) {
        // сохраняем курс
        entityManagerCourse.persist(course);
    }

    // найти все курсы по их количеству студентов
    @Override
    public List<Course> findAllByStudentsCount(int count) {
        return entityManagerCourse.createQuery("select course from Course course where course.students.size = ?", Course.class)
                .setParameter(0, count)
                .getResultList();
    }
}
