package ru.med.app.dto.request;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record ConditionCreateRequest(
        String clinicalStatus, // статус (активный, ремиссия и тд)
        String code, // код болезни/проблемы
        String bodySite, // Анатомическое расположение
        String severity, // тяжесть
        String description, // описание, пометки и тд
        LocalDate onsetDate, // дата начала
        LocalDate abatementDate, // дата завершения/ремиссии
        LocalDate recordedDate, // дата записи
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
