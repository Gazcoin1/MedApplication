package ru.med.app.service;

import org.springframework.security.core.Authentication;
import ru.med.app.dto.request.ProcedureCreateRequest;
import ru.med.app.dto.response.procedure.ProcedureResponse;
import ru.med.app.dto.response.procedure.ProcedureShortResponse;

import java.util.UUID;

public interface ProcedureService {
    ProcedureResponse createProcedure(Authentication authentication, ProcedureCreateRequest body);
    ProcedureResponse getProcedureResponse(Authentication authentication);
    ProcedureShortResponse getProcedureShortResponse(UUID procedureId);
}
