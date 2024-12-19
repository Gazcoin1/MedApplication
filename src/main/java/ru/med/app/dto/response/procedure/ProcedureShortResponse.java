package ru.med.app.dto.response.procedure;

import java.time.LocalDate;

public record ProcedureShortResponse(
        String category,
        String code,
        String description,
        String bodySite,
        String outcome,
        String report,
        LocalDate performedDate
) {
}
