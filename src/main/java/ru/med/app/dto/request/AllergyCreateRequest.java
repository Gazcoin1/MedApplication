package ru.med.app.dto.request;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record AllergyCreateRequest(
        String clinicalStatus,
        String type,
        String category,
        String code,
        String criticality,
        String description,
        String reactionSeverity,
        String reactionDescription,
        LocalDate onsetDate,
        LocalDate lastOccurrenceDate,
        LocalDate recordedDate,
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
