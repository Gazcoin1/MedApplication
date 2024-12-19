package ru.med.app.dto.request;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record ProcedureCreateRequest(
        String category,
        String code,
        String description,
        String bodySite,
        String outcome,
        String report,
        LocalDate performedDate,
        String doctorLastName, // о том, кто ставил диагноз
        String doctorFirstName,
        String doctorMiddleName,
        String doctorSpecialization,
        String recorderLastName, // о том, кто делал запись
        String recorderFirstName,
        String recorderMiddleName,
        String recorderSpecialization
) {
}
