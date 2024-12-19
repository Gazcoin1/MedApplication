package ru.med.app.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Builder
public record UserCreateRequest(
        @NotNull(message = "Введите свою фамилию.")
        @Pattern(regexp = "^[А-Я][а-я]{1,50}$", message = "ФИО, только буквы русского алфавита, первая буква заглавная, не более 50 символов.")
        String lastName,

        @NotNull(message = "Введите своё имя.")
        @Pattern(regexp = "^[А-Я][а-я]{1,50}$", message = "ФИО, только буквы русского алфавита, первая буква заглавная, не более 50 символов.")
        String firstName,

        @Pattern(regexp = "^[А-Я][а-я]{1,50}$", message = "ФИО, только буквы русского алфавита, первая буква заглавная, не более 50 символов.")
        String middleName,

        @NotNull(message = "Введите свой пол.")
        @Pattern(regexp = "^(Мужской|Женский)$", message = "Пол только мужской и женский.")
        String sex,

        @NotNull(message = "Введите дату рождения.")
        @Past(message = "Некорректная дата рождения.")
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        LocalDate birthDate,

        String address,

        @NotNull(message = "Введите номер телефона.")
        @Pattern(regexp = "7\\d{10}", message = "Формат телефона: 11 цифр, начинается с '7' ")
        String phoneNumber,

        @Email(message = "Неверный формат почты.")
        @NotNull(message = "Введите почту")
        String email,

        @NotNull(message = "Введите пароль")
        @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!?])[A-Za-z\\d!?]{8,50}$", message = "Пароль должен быть от 8 до 50 символов. Только латинские буквы, цифры и знаки '!?'. Обязательно наличие минимум 1 буквы верхнего и нижнего регистра, цифры и знака.")
        String password
) {
}
