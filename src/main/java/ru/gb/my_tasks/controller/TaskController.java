package ru.gb.my_tasks.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.gb.my_tasks.model.Task;
import ru.gb.my_tasks.model.TaskStatus;
import ru.gb.my_tasks.service.TaskService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Контроллер
 */
@Controller
@RequiredArgsConstructor
@Log
public class TaskController {
    private final TaskService taskService;

    /**
     * Метод передачи браузеру страницы с отображением всех задач с фильтром по статусу
     * @param status Принимает стутус задачи по индексу в классе TaskStatus
     * @param model Модель страницы
     * @return страница со списком отфильтрованных задач
     * Передает в консоль логи процесса выполнения метода
     */
    @GetMapping("/tasks/{status}")
    public String getAllTasks(@PathVariable("status") Integer status, Model model) {
        log.info("Попытка получить список задач из базы данных");
        List<Task> tasks = new ArrayList<>();
        String statusTitle = "";
        if (status == -1) {
            tasks = taskService.getAllTasks();
            statusTitle = "Все";
        }
        if (status >= 0 && status < TaskStatus.values().length) {
            TaskStatus taskStatus = TaskStatus.values()[status];
            statusTitle = taskStatus.getValue();
            tasks = taskService.getTasksByStatus(taskStatus);
        }
        log.info("Количество задач, полученных из базы данных: " + tasks.size());
        model.addAttribute("tasks", tasks);
        model.addAttribute("status_title", statusTitle);
        log.info("Переход к /tasks-list");
        return "/tasks-list";
    }


    /**
     * Метод подготовки формы страницы новой задачи
     * @param task Экземпляр задачи
     * @param model модель страницы
     * @return страница создания новой задачи
     */
    @GetMapping("/task-add")
    public String createTaskForm(Task task, Model model) {
        log.info("Создание формы /task-add");
         model.addAttribute("today", LocalDate.now());
        return "/task-add";
    }

    /**
     * Метод принимающий от браузера экземпляр новой задачи
     * @param task экземпляр новой задачи
     * @return перенаправляет страницу, подготовленную методом getAllTasks
     * с фильтром отображения списка не начатых задач
     * redirect:/tasks/0 - ноль это индекс не начатых задач в классе TaskStatus
     */
    @PostMapping("/task-add")
    public String addTask(Task task) {
        task.setDate(LocalDate.now());
        task.setStatus(TaskStatus.NOT_STARTED);
        log.info("Попытка добавить новую задачу в базу данных: " + task);
        taskService.addTask(task);
        log.info("Новая задача добавлена в базу данных");
        return "redirect:/tasks/0";
    }

    /**
     * Метод принимающий от браузера id задачи для её удаления из базы данных
     * @param id - идентификатор задачи
     * @return перенаправляет страницу, подготовленную методом getAllTasks
     * с фильтром отображения списка задач со статусом удаленной задачи
     */
    @GetMapping("/task-delete/{id}")
    public String deleteTask(@PathVariable("id") Long id) {
        int status = taskService.findById(id).getStatus().ordinal();
        log.info("Попытка удалить задачу с id = " + id);
        taskService.deleteTaskById(id);
        log.info("Задача успешно удалена из базы данных");
        return "redirect:/tasks/" + status;
    }

    /**
     * Метод, изменения статуса задачи
     * @param id идентификатор задачи
     * @param status индекс нового статуса задачи, соответствующий индексу статуса в классе TaskStatus
     * @return перенаправляет страницу, подготовленную методом getAllTasks
     *     c фильтром отображения списка задач со статусом, соответствующим присвоенному
     */
    @GetMapping("/task-update/{id}/{status}")
    public String updateTaskStatus(@PathVariable("id") Long id, @PathVariable("status") Integer status) {
        log.info("Попытка изменить статус задачи с id = " + id + " на статус " + status);
        Task task = taskService.findById(id);
        if (!task.getStatus().equals(TaskStatus.values()[status])) {
            task.setStatus(TaskStatus.values()[status]);
            taskService.updateTaskStatus(task);
            log.info("Статус задачи успешно обновлен");
        } else log.info("Статус задачи не менялся");
        return "redirect:/tasks/" + status;
    }

}
