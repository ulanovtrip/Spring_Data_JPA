package ru.itis.spring.models;

import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
// чтобы не печатать course внутри lesson, иначе stackoverflow
@ToString(exclude = "course")
@Entity
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // значит, что БД сама будет генерировать
    private Long id;

    private String name;

    // внешний ключ для курса
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
}
