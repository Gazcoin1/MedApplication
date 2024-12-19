package ru.med.app.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.med.app.dto.request.ProcedureCreateRequest;
import ru.med.app.dto.response.procedure.ProcedureResponse;
import ru.med.app.dto.response.procedure.ProcedureShortResponse;
import ru.med.app.service.ProcedureService;

import java.util.UUID;

@RestController
@RequestMapping("med")
@RequiredArgsConstructor
public class ProcedureController {
    private final ProcedureService procedureService;

    @PostMapping("/procedure")
    public ProcedureResponse createProcedure(Authentication authentication, @RequestBody @Valid ProcedureCreateRequest body) {
        return procedureService.createProcedure(authentication, body);
    }

    @GetMapping("/procedure")
    public ProcedureResponse getProcedure(Authentication authentication) {
        return procedureService.getProcedureResponse(authentication);
    }

    @GetMapping("/procedure/{procedureId}")
    public ProcedureShortResponse getProcedureShort(String procedureId) {
        UUID id = UUID.fromString(procedureId);
        return procedureService.getProcedureShortResponse(id);
    }
}
