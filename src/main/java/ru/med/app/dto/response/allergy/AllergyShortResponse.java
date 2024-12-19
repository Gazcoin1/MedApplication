package ru.med.app.dto.response.allergy;

import java.time.LocalDate;

public record AllergyShortResponse(
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
        LocalDate recordedDate
) {
}
