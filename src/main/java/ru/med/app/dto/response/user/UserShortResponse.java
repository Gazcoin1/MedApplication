package ru.med.app.dto.response.user;

public record UserShortResponse(
        String lastName,
        String firstName,
        String email,
        String phoneNumber
) {
}
