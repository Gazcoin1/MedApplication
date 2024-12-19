package ru.med.app.mapper;

import org.springframework.stereotype.Component;
import ru.med.app.dto.request.ProcedureCreateRequest;
import ru.med.app.dto.response.procedure.ProcedureResponse;
import ru.med.app.dto.response.procedure.ProcedureShortResponse;
import ru.med.app.entity.Doctor;
import ru.med.app.entity.Procedure;
import ru.med.app.entity.User;

@Component
public class ProcedureMapper {
    public static Procedure mapProcedureCreateRequestToProcedure(
            ProcedureCreateRequest body,
            User user,
            Doctor doctor,
            Doctor recorder
    ) {
        Procedure procedure = new Procedure();
        procedure.setPatient(user);
        procedure.setCategory(body.category());
        procedure.setCode(body.code());
        procedure.setDescription(body.description());
        procedure.setBodySite(body.bodySite());
        procedure.setOutcome(body.outcome());
        procedure.setReport(body.report());
        procedure.setPerformedDate(body.performedDate());
        procedure.setDoctor(doctor);
        procedure.setRecorder(recorder);

        return procedure;
    }

    public static ProcedureResponse mapProcedureToResponse(Procedure procedure) {
        return new ProcedureResponse(
                procedure.getId().toString(),
                procedure.getPatient().getId().toString(),
                procedure.getCategory(),
                procedure.getCode(),
                procedure.getDescription(),
                procedure.getBodySite(),
                procedure.getOutcome(),
                procedure.getReport(),
                procedure.getPerformedDate(),
                procedure.getDoctor().getId().toString(),
                procedure.getRecorder().getId().toString()
        );
    }

    public static ProcedureShortResponse mapProcedureToShortResponse(Procedure procedure) {
        return new ProcedureShortResponse(
                procedure.getCategory(),
                procedure.getCode(),
                procedure.getDescription(),
                procedure.getBodySite(),
                procedure.getOutcome(),
                procedure.getReport(),
                procedure.getPerformedDate()
        );
    }
}
