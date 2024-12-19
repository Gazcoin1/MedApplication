package ru.med.app.dto.response.user;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record UserResponse(
        String id,
        String lastName,
        String firstName,
        String middleName,
        String sex,
        LocalDate birthDate,
        String address,
        String phoneNumber,
        String email,
        LocalDateTime createdTime,
        LocalDateTime updatedTime
) {
}
