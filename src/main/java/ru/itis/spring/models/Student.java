package ru.itis.spring.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity // это позволит не использовать файлы hbm.xml
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;

    // многие ко многим, может быть много курсов у студента
    @ManyToMany // когда такая связь, то нужна третья таблица
    @JoinTable(joinColumns = @JoinColumn(name = "student_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "course_id", referencedColumnName = "id"))
    private List<Course> courses;
}
