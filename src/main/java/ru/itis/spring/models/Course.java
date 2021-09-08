package ru.itis.spring.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@ToString(exclude = {"lessons", "students"})
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    // у курса может быть много уроков
    @OneToMany(mappedBy = "course")
    private List<Lesson> lessons;

    // у курса может быть много студентов
    @ManyToMany(mappedBy = "courses") // возьмём за основу правило, которое напишем у студента
    private List<Student> students;
}
