package ru.gb.my_tasks.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.time.LocalDate;


/**
 * Класс задачи с полями:
 * -уникальный идентификатор - присваивается базой данных
 * - описание задачи
 * - статус задачи
 * - дата создания задачи
 */
@Entity
@EntityListeners(AuditingEntityListener.class)
@RequiredArgsConstructor
@Table(name = "tasks")
@Setter
@Getter
@ToString
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "description", nullable = false)
    private String description;
    @Column(name = "status")
    private TaskStatus status;
    @Column(name = "date")
    private LocalDate date;

}
