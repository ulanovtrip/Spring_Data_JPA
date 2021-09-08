package ru.itis.spring.repositories;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.spring.models.Student;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class StudentRepositoryJpaImpl implements StudentRepository {

    @PersistenceContext
    private EntityManager entityManagerStud;

    @Override
    @Transactional
    public void save(Student student) {
        entityManagerStud.persist(student);
    }
}
