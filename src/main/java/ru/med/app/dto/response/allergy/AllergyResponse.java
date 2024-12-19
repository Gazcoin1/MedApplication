package ru.med.app.dto.response.allergy;

import java.time.LocalDate;

public record AllergyResponse(
        String id,
        String userId,
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
        String doctorId,
        String recorderId
) {
}
