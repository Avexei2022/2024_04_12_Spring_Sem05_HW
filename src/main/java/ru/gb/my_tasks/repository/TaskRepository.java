package ru.gb.my_tasks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gb.my_tasks.model.Task;
import ru.gb.my_tasks.model.TaskStatus;

import java.util.List;

/**
 * Репозиторий с дополнительным методом получения списка задач по их статусу
 */
@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findAllByStatus(TaskStatus status);
}
