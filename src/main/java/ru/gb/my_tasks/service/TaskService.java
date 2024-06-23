package ru.gb.my_tasks.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.my_tasks.model.Task;
import ru.gb.my_tasks.model.TaskStatus;
import ru.gb.my_tasks.repository.TaskRepository;

import java.util.List;

/**
 * Сервис работы с репозиторием
 */
@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;

    /**
     * Метод добавления/изменения задачи
     * @param task Задача
     */
    public void addTask(Task task) {
        taskRepository.save(task);
    }

    /**
     * Метод получения из базы данных всех задач
     * @return Список всех задач
     */
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    /**
     * Метод получения задачи по id
     * @param id
     * @return Экземпляр задачи
     */
    public Task findById(Long id) {
        return taskRepository.findById(id).orElseThrow();
    }

    /**
     * Метод получения из базы данных списка задач с фильтром по статусу
     * @param status Статус задачи
     * @return Список задач с запрошенным статусом
     */
    public List<Task> getTasksByStatus(TaskStatus status) {
        return taskRepository.findAllByStatus(status);
    }

    /**
     * Метод изменения статуса задачи
     * @param task Задача с измененным статусом
     */
    public void updateTaskStatus(Task task) {
        taskRepository.save(task);
    }

    /**
     * Удаление задачи из базы данных по id
     * @param id
     */
    public void deleteTaskById(Long id) {
        taskRepository.deleteById(id);
    }
}
