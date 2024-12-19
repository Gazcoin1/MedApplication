package ru.med.app.dto.response.doctor;

public record DoctorResponse(
        String id,
        String lastName,
        String firstName,
        String middleName,
        String specialization
) {
}
