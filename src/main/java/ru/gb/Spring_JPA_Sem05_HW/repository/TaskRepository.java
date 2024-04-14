package ru.gb.Spring_JPA_Sem05_HW.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gb.Spring_JPA_Sem05_HW.model.Task;
import ru.gb.Spring_JPA_Sem05_HW.model.TaskStatus;

import java.util.List;

/**
 * Репозиторий с дополнительным методом получения списка задач по их статусу
 */
@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findAllByStatus(TaskStatus status);
}
