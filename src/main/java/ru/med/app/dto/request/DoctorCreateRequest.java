package ru.med.app.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;

@Builder
public record DoctorCreateRequest(
        @NotNull(message = "Введите фамилию врача.")
        @Pattern(regexp = "^[А-Я][а-я]{1,50}$", message = "ФИО, только буквы русского алфавита, первая буква заглавная, не более 50 символов.")
        String lastName,

        @NotNull(message = "Введите имя врача.")
        @Pattern(regexp = "^[А-Я][а-я]{1,50}$", message = "ФИО, только буквы русского алфавита, первая буква заглавная, не более 50 символов.")
        String firstName,

        @Pattern(regexp = "^[А-Я][а-я]{1,50}$", message = "ФИО, только буквы русского алфавита, первая буква заглавная, не более 50 символов.")
        String middleName,

        @Pattern(regexp = "^[А-Я][а-я]{1,50}$", message = "Только буквы русского алфавита, первая буква заглавная, не более 50 символов.")
        String specialization
) {
}
