package ru.med.app.dto.response.doctor;

public record DoctorShortResponse(
        String lastName,
        String firstName,
        String middleName
) {
}
