package ru.med.app.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.med.app.dto.request.ConditionCreateRequest;
import ru.med.app.dto.response.condition.ConditionResponse;
import ru.med.app.dto.response.condition.ConditionShortResponse;
import ru.med.app.service.ConditionService;

import java.util.UUID;

@RestController
@RequestMapping("med")
@RequiredArgsConstructor
public class ConditionController {
    private final ConditionService conditionService;

    @PostMapping("/condition")
    public ConditionResponse createCondition(Authentication authentication, @RequestBody @Valid ConditionCreateRequest body) {
        return conditionService.createCondition(authentication, body);
    }

    @GetMapping("/condition")
    public ConditionResponse getCondition(Authentication authentication) {
        return conditionService.getConditionResponse(authentication);
    }

    @GetMapping("/condition/{conditionId}")
    public ConditionShortResponse getConditionInfo(String conditionId) {
        UUID id = UUID.fromString(conditionId);
        return conditionService.getConditionShortResponse(id);
    }
}
