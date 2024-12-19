package ru.med.app.dto.response.condition;

import java.time.LocalDate;

public record ConditionShortResponse(
        String clinicalStatus,
        String code,
        String bodySite,
        String severity,
        String description,
        LocalDate onsetDate,
        LocalDate abatementDate,
        LocalDate recordedDate
) {
}
