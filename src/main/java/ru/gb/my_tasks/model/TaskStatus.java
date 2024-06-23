package ru.gb.my_tasks.model;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Предопределенные значения статуса задачи
 */
@RequiredArgsConstructor
@Getter
public enum TaskStatus {
    NOT_STARTED("Hе начата"),
    IN_PROGRESS("В процессе"),
    COMPLETED("Завершена");

    private final String value;

}
