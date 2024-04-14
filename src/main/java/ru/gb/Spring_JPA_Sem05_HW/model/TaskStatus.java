package ru.gb.Spring_JPA_Sem05_HW.model;


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
