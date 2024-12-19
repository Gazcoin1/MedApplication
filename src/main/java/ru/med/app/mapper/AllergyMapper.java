package ru.med.app.mapper;

import org.springframework.stereotype.Component;
import ru.med.app.dto.request.AllergyCreateRequest;
import ru.med.app.dto.response.allergy.AllergyResponse;
import ru.med.app.dto.response.allergy.AllergyShortResponse;
import ru.med.app.entity.Allergy;
import ru.med.app.entity.Doctor;
import ru.med.app.entity.User;

@Component
public class AllergyMapper {
    public static Allergy mapAllergyCreateRequestToAllergy(
            AllergyCreateRequest body,
            User user,
            Doctor doctor,
            Doctor recorder
    ) {
        Allergy allergy = new Allergy();
        allergy.setPatient(user);
        allergy.setClinicalStatus(body.clinicalStatus());
        allergy.setType(body.type());
        allergy.setCategory(body.category());
        allergy.setCode(body.code());
        allergy.setCriticality(body.criticality());
        allergy.setDescription(body.description());
        allergy.setReactionSeverity(body.reactionSeverity());
        allergy.setReactionDescription(body.reactionDescription());
        allergy.setOnsetDate(body.onsetDate());
        allergy.setLastOccurrenceDate(body.lastOccurrenceDate());
        allergy.setRecordedDate(body.recordedDate());
        allergy.setDoctor(doctor);
        allergy.setRecorder(recorder);

        return allergy;
    }

    public static AllergyResponse mapAllergyToResponse(Allergy allergy) {
        return new AllergyResponse(
                allergy.getId().toString(),
                allergy.getPatient().getId().toString(),
                allergy.getClinicalStatus(),
                allergy.getType(),
                allergy.getCategory(),
                allergy.getCode(),
                allergy.getCriticality(),
                allergy.getDescription(),
                allergy.getReactionSeverity(),
                allergy.getReactionDescription(),
                allergy.getOnsetDate(),
                allergy.getLastOccurrenceDate(),
                allergy.getRecordedDate(),
                allergy.getDoctor().getId().toString(),
                allergy.getRecorder().getId().toString()
        );
    }

    public static AllergyShortResponse mapAllergyToShortResponse(Allergy allergy) {
        return new AllergyShortResponse(
                allergy.getClinicalStatus(),
                allergy.getType(),
                allergy.getCategory(),
                allergy.getCode(),
                allergy.getCriticality(),
                allergy.getDescription(),
                allergy.getReactionSeverity(),
                allergy.getReactionDescription(),
                allergy.getOnsetDate(),
                allergy.getLastOccurrenceDate(),
                allergy.getRecordedDate()
        );
    }
}
