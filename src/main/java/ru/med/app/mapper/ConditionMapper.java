package ru.med.app.mapper;

import org.springframework.stereotype.Component;
import ru.med.app.dto.request.ConditionCreateRequest;
import ru.med.app.dto.response.condition.ConditionResponse;
import ru.med.app.dto.response.condition.ConditionShortResponse;
import ru.med.app.entity.Condition;
import ru.med.app.entity.Doctor;
import ru.med.app.entity.User;

@Component
public class ConditionMapper {
    public static Condition mapConditionCreateRequestToCondition(
            ConditionCreateRequest body,
            User user,
            Doctor doctor,
            Doctor recorder
    ) {
        Condition condition = new Condition();
        condition.setPatient(user);
        condition.setClinicalStatus(body.clinicalStatus());
        condition.setCode(body.code());
        condition.setBodySite(body.bodySite());
        condition.setSeverity(body.severity());
        condition.setDescription(body.description());
        condition.setOnsetDate(body.onsetDate());
        condition.setAbatementDate(body.abatementDate());
        condition.setRecordedDate(body.recordedDate());
        condition.setDoctor(doctor);
        condition.setRecorder(recorder);

        return condition;
    }

    public static ConditionResponse mapConditionToResponse(Condition condition) {
        return new ConditionResponse(
                condition.getId().toString(),
                condition.getPatient().getId().toString(),
                condition.getClinicalStatus(),
                condition.getCode(),
                condition.getBodySite(),
                condition.getSeverity(),
                condition.getDescription(),
                condition.getOnsetDate(),
                condition.getAbatementDate(),
                condition.getRecordedDate(),
                condition.getDoctor().getId().toString(),
                condition.getRecorder().getId().toString()
        );
    }

    public static ConditionShortResponse mapConditionToShortResponse(Condition condition) {
        return new ConditionShortResponse(
                condition.getClinicalStatus(),
                condition.getCode(),
                condition.getBodySite(),
                condition.getSeverity(),
                condition.getDescription(),
                condition.getOnsetDate(),
                condition.getAbatementDate(),
                condition.getRecordedDate()
        );
    }
}
