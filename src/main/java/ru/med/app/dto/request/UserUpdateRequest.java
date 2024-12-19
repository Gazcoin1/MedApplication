package ru.med.app.dto.request;

import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public record UserUpdateRequest(

        @Pattern(regexp = "^[А-Я][а-я]{1,50}$", message = "ФИО, только буквы русского алфавита, первая буква заглавная, не более 50 символов.")
        String lastName,

        @Pattern(regexp = "^[А-Я][а-я]{1,50}$", message = "ФИО, только буквы русского алфавита, первая буква заглавная, не более 50 символов.")
        String firstName,

        @Pattern(regexp = "^[А-Я][а-я]{1,50}$", message = "ФИО, только буквы русского алфавита, первая буква заглавная, не более 50 символов.")
        String middleName,

        @Past(message = "Некорректная дата рождения.")
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        LocalDate birthDate,

        @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!?])[A-Za-z\\d!?]{8,64}$", message = "Пароль должен быть от 8 до 64 символов. Только латинские буквы, цифры и знаки '!?'. Обязательно наличие минимум 1 буквы верхнего и нижнего регистра, цифры и знака.")
        String password,

        String address
) {
}
